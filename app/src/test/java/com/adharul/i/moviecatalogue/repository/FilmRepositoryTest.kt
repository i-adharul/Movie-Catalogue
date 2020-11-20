package com.adharul.i.moviecatalogue.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adharul.i.moviecatalogue.dataremote.RemoteDataSource
import com.adharul.i.moviecatalogue.utils.DataDummy
import com.adharul.i.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class FilmRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote)

    private val popularMovieResponse = DataDummy.generateRemotePopularMovie()
    private val popularTvShowResponse = DataDummy.generateRemotePopularTvShow()
    private val movieId = popularMovieResponse[0].filmId as String
    private val tvShowId = popularTvShowResponse[0].filmId as String
    private val detailMovieResponse = DataDummy.generateRemoteMovieDetails()
    private val detailTvShowResponse = DataDummy.generateRemoteTvShowDetails()

    @Test
    fun getPopularMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.PopularMovieCallback)
                .onPopularMovieCallback(popularMovieResponse)
            null
        }.`when`(remote).getPopularMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getPopularMovie())
        verify(remote).getPopularMovie(any())
        assertNotNull(movieEntities)
        assertEquals(popularMovieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getPopularTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.PopularTvShowCallback)
                .onPopularTvShowCallback(popularTvShowResponse)
            null
        }.`when`(remote).getPopularTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getPopularTvShow())
        verify(remote).getPopularTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(popularTvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMovieDetails() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.MovieDetailsCallback)
                .onMovieDetailsCallback(detailMovieResponse)
            null
        }.`when`(remote).getMovieDetails(eq(movieId), any())
        val movieDetailsEntities =
            LiveDataTestUtil.getValue(filmRepository.getMovieDetails(movieId))
        verify(remote).getMovieDetails(eq(movieId), any())
        assertNotNull(movieDetailsEntities)
        assertEquals(detailMovieResponse.language, movieDetailsEntities.language)
        assertEquals(detailMovieResponse.overview, movieDetailsEntities.overview)
        assertEquals(detailMovieResponse.posterUrl, movieDetailsEntities.posterUrl)
        assertEquals(detailMovieResponse.runtime, movieDetailsEntities.runtime)
        assertEquals(detailMovieResponse.status, movieDetailsEntities.status)
    }

    @Test
    fun getTvShowDetails() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.TvShowDetailsCallback)
                .onTvShowDetailsCallback(detailTvShowResponse)
            null
        }.`when`(remote).getTvShowDetails(eq(tvShowId), any())
        val tvShowDetailsEntities =
            LiveDataTestUtil.getValue(filmRepository.getTvShowDetails(tvShowId))
        verify(remote).getTvShowDetails(eq(tvShowId), any())
        assertNotNull(tvShowDetailsEntities)
        assertEquals(detailTvShowResponse.tvShowName, tvShowDetailsEntities.tvShowName)
        assertEquals(detailTvShowResponse.language, tvShowDetailsEntities.language)
        assertEquals(detailTvShowResponse.overview, tvShowDetailsEntities.overview)
        assertEquals(detailTvShowResponse.posterUrl, tvShowDetailsEntities.posterUrl)
        assertEquals(detailTvShowResponse.epsRuntime, tvShowDetailsEntities.epsRuntime)
        assertEquals(detailTvShowResponse.status, tvShowDetailsEntities.status)
    }
}