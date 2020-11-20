package com.adharul.i.moviecatalogue.viewmodel

import com.adharul.i.moviecatalogue.dataremote.RemoteDataSource
import com.adharul.i.moviecatalogue.dataremote.utils.AccessingMovieDbApi
import com.adharul.i.moviecatalogue.repository.FilmRepository

object FilmRepositoryInjection {
    fun provideRepository(): FilmRepository {
        val remoteDataSource = RemoteDataSource.getInstance(AccessingMovieDbApi())

        return FilmRepository.getInstance(remoteDataSource)
    }
}