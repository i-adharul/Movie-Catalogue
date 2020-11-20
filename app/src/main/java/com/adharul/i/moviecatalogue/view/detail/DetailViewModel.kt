package com.adharul.i.moviecatalogue.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adharul.i.moviecatalogue.datalocal.entity.MovieCreditsEntity
import com.adharul.i.moviecatalogue.datalocal.entity.MovieEntity
import com.adharul.i.moviecatalogue.datalocal.entity.TvShowCreditsEntity
import com.adharul.i.moviecatalogue.datalocal.entity.TvShowEntity
import com.adharul.i.moviecatalogue.repository.FilmRepository

class DetailViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    private lateinit var filmId: String
    private var fragment: Int? = null

    fun setSelectedFilm(filmID: String, fragment: Int) {
        this.filmId = filmID
        this.fragment = fragment
    }

    fun getMovieDetails(): LiveData<MovieEntity> =
        filmRepository.getMovieDetails(filmId)

    fun getMovieCredits(): LiveData<List<MovieCreditsEntity>> =
        filmRepository.getMovieCredits(filmId)

    fun getTvShowDetails(): LiveData<TvShowEntity> =
        filmRepository.getTvShowDetails(filmId)

    fun getTvShowCredits(): LiveData<List<TvShowCreditsEntity>> =
        filmRepository.getTvShowCredits(filmId)
}