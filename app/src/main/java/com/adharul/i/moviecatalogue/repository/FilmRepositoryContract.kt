package com.adharul.i.moviecatalogue.repository

import androidx.lifecycle.LiveData
import com.adharul.i.moviecatalogue.datalocal.entity.*

interface FilmRepositoryContract {
    fun getPopularMovie(): LiveData<List<FilmEntity>>

    fun getMovieDetails(movieId: String): LiveData<MovieEntity>

    fun getMovieCredits(movieId: String): LiveData<List<MovieCreditsEntity>>

    fun getPopularTvShow(): LiveData<List<FilmEntity>>

    fun getTvShowDetails(tvShowId: String): LiveData<TvShowEntity>

    fun getTvShowCredits(tvShowId: String): LiveData<List<TvShowCreditsEntity>>
}