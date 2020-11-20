package com.adharul.i.moviecatalogue.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adharul.i.moviecatalogue.datalocal.entity.FilmEntity
import com.adharul.i.moviecatalogue.repository.FilmRepository

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getPopularMovieList(): LiveData<List<FilmEntity>> =
        filmRepository.getPopularMovie()

    fun getPopularTvShowList(): LiveData<List<FilmEntity>> =
        filmRepository.getPopularTvShow()
}