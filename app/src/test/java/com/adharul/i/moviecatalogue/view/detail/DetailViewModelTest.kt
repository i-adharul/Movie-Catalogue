package com.adharul.i.moviecatalogue.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adharul.i.moviecatalogue.datalocal.entity.MovieEntity
import com.adharul.i.moviecatalogue.datalocal.entity.TvShowEntity
import com.adharul.i.moviecatalogue.repository.FilmRepository
import com.adharul.i.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateMovieDetails()
    private val movieId = DataDummy.generatePopularMovie()[0].filmId as String
    private val fragmentMovie = 1
    private val dummyTvShow = DataDummy.generateTvShowDetails()
    private val tvShowId = DataDummy.generatePopularTvShow()[0].filmId as String
    private val fragmentTvShow = 2

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Test
    fun getMovieDetails() {
        viewModel = DetailViewModel(filmRepository)
        viewModel.setSelectedFilm(movieId, fragmentMovie)

        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(filmRepository.getMovieDetails(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetails().value as MovieEntity
        verify(filmRepository).getMovieDetails(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieTitle, movieEntity.movieTitle)
        assertEquals(dummyMovie.runtime, movieEntity.runtime)
        assertEquals(dummyMovie.language, movieEntity.language)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.posterUrl, movieEntity.posterUrl)
        assertEquals(dummyMovie.status, movieEntity.status)

        viewModel.getMovieDetails().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetails() {
        viewModel = DetailViewModel(filmRepository)
        viewModel.setSelectedFilm(tvShowId, fragmentTvShow)

        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(filmRepository.getTvShowDetails(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShowDetails().value as TvShowEntity
        verify(filmRepository).getTvShowDetails(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.tvShowName, tvShowEntity.tvShowName)
        assertEquals(dummyTvShow.epsRuntime, tvShowEntity.epsRuntime)
        assertEquals(dummyTvShow.language, tvShowEntity.language)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.posterUrl, tvShowEntity.posterUrl)
        assertEquals(dummyTvShow.status, tvShowEntity.status)

        viewModel.getTvShowDetails().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}