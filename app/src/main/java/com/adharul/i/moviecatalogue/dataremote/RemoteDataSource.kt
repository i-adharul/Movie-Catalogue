package com.adharul.i.moviecatalogue.dataremote

import com.adharul.i.moviecatalogue.dataremote.response.*
import com.adharul.i.moviecatalogue.dataremote.utils.AccessingMovieDbApi

class RemoteDataSource private constructor(private val accessingMovieDbApi: AccessingMovieDbApi) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(accessingMovieDbApi: AccessingMovieDbApi): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(accessingMovieDbApi)
            }
    }

    fun getPopularMovie(callback: PopularMovieCallback) {
        accessingMovieDbApi.popularMovieRequest(object : AccessingMovieDbApi.PopularMovieContract {
            override fun onPopularMovieResponses(popularMovie: List<FilmResponse>) {
                callback.onPopularMovieCallback(popularMovie)
            }
        })
    }

    fun getMovieDetails(movieId: String, callback: MovieDetailsCallback) {
        accessingMovieDbApi.movieDetailsRequest(
            movieId,
            object : AccessingMovieDbApi.MovieDetailsContract {
                override fun onMovieDetailsResponses(movieDetails: MovieDetailsResponse) {
                    callback.onMovieDetailsCallback(movieDetails)
                }
            })
    }

    fun getMovieCredits(movieId: String, callback: MovieCreditsCallback){
        accessingMovieDbApi.movieCreditsRequest(
            movieId, object : AccessingMovieDbApi.MovieCreditsContract{
                override fun onMovieCastsResponse(movieCredits: List<MovieCreditsResponse>) {
                    callback.onMovieCreditsCallback(movieCredits)
                }
            }
        )
    }

    fun getPopularTvShow(callback: PopularTvShowCallback) {
        accessingMovieDbApi.popularTvShowRequest(object :
            AccessingMovieDbApi.PopularTvShowContract {
            override fun onPopularTvShowResponses(popularTvShow: List<FilmResponse>) {
                callback.onPopularTvShowCallback(popularTvShow)
            }
        })
    }

    fun getTvShowDetails(tvShowId: String, callback: TvShowDetailsCallback) {
        accessingMovieDbApi.tvShowDetailsRequest(
            tvShowId,
            object : AccessingMovieDbApi.TvShowDetailsContract {
                override fun onTvShowDetailsResponses(tvShowDetails: TvShowDetailsResponse) {
                    callback.onTvShowDetailsCallback(tvShowDetails)
                }
            })
    }

    fun getTvShowCredits(tvShowId: String, callback: TvShowCreditsCallback){
        accessingMovieDbApi.tvShowCreditsRequest(
            tvShowId, object : AccessingMovieDbApi.TvShowCreditsContract{
                override fun onTvShowCastsResponse(tvShowCredits: List<TvShowCreditsResponse>) {
                    callback.onTvShowCreditCallback(tvShowCredits)
                }
            }
        )
    }

    interface PopularMovieCallback {
        fun onPopularMovieCallback(popularMovie: List<FilmResponse>)
    }

    interface MovieDetailsCallback {
        fun onMovieDetailsCallback(movieDetails: MovieDetailsResponse)
    }

    interface MovieCreditsCallback {
        fun onMovieCreditsCallback(movieCredits: List<MovieCreditsResponse>)
    }

    interface PopularTvShowCallback {
        fun onPopularTvShowCallback(popularTvShow: List<FilmResponse>)
    }

    interface TvShowDetailsCallback {
        fun onTvShowDetailsCallback(tvShowDetails: TvShowDetailsResponse)
    }

    interface TvShowCreditsCallback{
        fun onTvShowCreditCallback(tvShowCredits: List<TvShowCreditsResponse>)
    }
}