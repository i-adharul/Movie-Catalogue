package com.adharul.i.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adharul.i.moviecatalogue.datalocal.entity.*
import com.adharul.i.moviecatalogue.dataremote.RemoteDataSource
import com.adharul.i.moviecatalogue.dataremote.response.*

class FakeFilmRepository(private val remoteDataSource: RemoteDataSource) :
    FilmRepositoryContract {

    override fun getPopularMovie(): LiveData<List<FilmEntity>> {
        val popularMovieListResult = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getPopularMovie(object : RemoteDataSource.PopularMovieCallback {
            override fun onPopularMovieCallback(popularMovie: List<FilmResponse>) {
                val popularMovieList = ArrayList<FilmEntity>()
                for (response in popularMovie) {
                    val film = FilmEntity(
                        response.filmId,
                        response.posterUrl
                    )
                    popularMovieList.add(film)
                }
                popularMovieListResult.postValue(popularMovieList)
            }
        })
        return popularMovieListResult
    }

    override fun getPopularTvShow(): LiveData<List<FilmEntity>> {
        val popularTvShowListResult = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getPopularTvShow(object : RemoteDataSource.PopularTvShowCallback {
            override fun onPopularTvShowCallback(popularTvShow: List<FilmResponse>) {
                val popularTvShowList = ArrayList<FilmEntity>()
                for (response in popularTvShow) {
                    val film = FilmEntity(
                        response.filmId,
                        response.posterUrl
                    )
                    popularTvShowList.add(film)
                }
                popularTvShowListResult.postValue(popularTvShowList)
            }
        })
        return popularTvShowListResult
    }

    override fun getMovieDetails(movieId: String): LiveData<MovieEntity> {
        val movieDetailsResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieDetails(
            movieId,
            object : RemoteDataSource.MovieDetailsCallback {
                override fun onMovieDetailsCallback(movieDetails: MovieDetailsResponse) {
                    val film = MovieEntity(
                        movieDetails.movieTitle,
                        movieDetails.posterUrl,
                        movieDetails.overview,
                        movieDetails.status,
                        movieDetails.language,
                        movieDetails.runtime
                    )
                    movieDetailsResult.postValue(film)
                }
            })
        return movieDetailsResult
    }

    override fun getMovieCredits(movieId: String): LiveData<List<MovieCreditsEntity>> {
        val movieCreditsResult = MutableLiveData<List<MovieCreditsEntity>>()
        remoteDataSource.getMovieCredits(
            movieId,
            object : RemoteDataSource.MovieCreditsCallback{
                override fun onMovieCreditsCallback(movieCredits: List<MovieCreditsResponse>) {
                    val castList = ArrayList<MovieCreditsEntity>()
                    for (response in movieCredits) {
                        val cast = MovieCreditsEntity(
                            response.artistName,
                            response.castPosition,
                            response.photoUrl
                        )
                        castList.add(cast)
                    }
                    movieCreditsResult.postValue(castList)
                }
            }
        )
        return movieCreditsResult
    }

    override fun getTvShowDetails(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowDetailsResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShowDetails(
            tvShowId,
            object : RemoteDataSource.TvShowDetailsCallback {
                override fun onTvShowDetailsCallback(tvShowDetails: TvShowDetailsResponse) {
                    val film = TvShowEntity(
                        tvShowDetails.tvShowName,
                        tvShowDetails.posterUrl,
                        tvShowDetails.overview,
                        tvShowDetails.status,
                        tvShowDetails.language,
                        tvShowDetails.epsRuntime
                    )
                    tvShowDetailsResult.postValue(film)
                }
            })
        return tvShowDetailsResult
    }

    override fun getTvShowCredits(tvShowId: String): LiveData<List<TvShowCreditsEntity>> {
        val tvShowCreditsResult = MutableLiveData<List<TvShowCreditsEntity>>()
        remoteDataSource.getTvShowCredits(
            tvShowId,
            object : RemoteDataSource.TvShowCreditsCallback{
                override fun onTvShowCreditCallback(tvShowCredits: List<TvShowCreditsResponse>) {
                    val castList = ArrayList<TvShowCreditsEntity>()
                    for (response in tvShowCredits) {
                        val cast = TvShowCreditsEntity(
                            response.artistName,
                            response.castPosition,
                            response.photoUrl
                        )
                        castList.add(cast)
                    }
                    tvShowCreditsResult.postValue(castList)
                }
            }
        )
        return tvShowCreditsResult
    }
}