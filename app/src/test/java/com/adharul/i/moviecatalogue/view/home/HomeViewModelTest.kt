package com.adharul.i.moviecatalogue.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adharul.i.moviecatalogue.datalocal.entity.FilmEntity
import com.adharul.i.moviecatalogue.repository.FilmRepository
import com.adharul.i.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(filmRepository)
    }

    @Test
    fun getPopularMovieList() {
        val dummyMovies = DataDummy.generatePopularMovie()
        val movies = MutableLiveData<List<FilmEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getPopularMovie()).thenReturn(movies)
        val movieEntities = viewModel.getPopularMovieList().value
        verify(filmRepository).getPopularMovie()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getPopularMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getPopularTvShowList() {
        val dummyTvShows = DataDummy.generatePopularTvShow()
        val tvShows = MutableLiveData<List<FilmEntity>>()
        tvShows.value = dummyTvShows

        `when`(filmRepository.getPopularTvShow()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getPopularTvShowList().value
        verify(filmRepository).getPopularTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getPopularTvShowList().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}