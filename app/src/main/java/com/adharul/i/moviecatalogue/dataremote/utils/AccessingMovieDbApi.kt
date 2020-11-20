package com.adharul.i.moviecatalogue.dataremote.utils

import android.util.Log
import com.adharul.i.moviecatalogue.BuildConfig
import com.adharul.i.moviecatalogue.dataremote.response.*
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import org.json.JSONObject

class AccessingMovieDbApi {
    private val apiKey = BuildConfig.API_KEY
    private val popularMovieUrl = BuildConfig.POPULAR_MOVIE_URL
    private val movieDetailUrl = BuildConfig.MOVIE_DETAIL_URL
    private val movieCreditUrl = BuildConfig.MOVIE_CREDIT_URL
    private val popularTvShowUrl = BuildConfig.POPULAR_TVSHOW_URL
    private val tvShowDetailUrl = BuildConfig.TVSHOW_DETAIL_URL
    private val tvShowCreditUrl = BuildConfig.TVSHOW_CREDIT_URL

    companion object {
        private const val TAG = "TAG"

        //Path Parameter
        private const val API_KEY = "api_key"
        private const val TV_ID = "tv_id"
        private const val MOVIE_ID = "movie_id"

        //Json Key
        private const val RESULTS = "results"
        private const val CAST = "cast"
        private const val CHARACTER = "character"
        private const val ID = "id"
        private const val POSTER_PATH = "poster_path"
        private const val PROFILE_PATH = "profile_path"
        private const val TITLE = "title"
        private const val NAME = "name"  //title for tvshow and name for artist cast
        private const val STATUS = "status"
        private const val RUNTIME = "runtime"
        private const val EPS_RUNTIME = "episode_run_time"
        private const val OVERVIEW = "overview"
        private const val LANGUAGE = "original_language"
    }

    fun popularMovieRequest(callback: PopularMovieContract) {
        val list = ArrayList<FilmResponse>()
        AndroidNetworking.get(popularMovieUrl)
            .addPathParameter(API_KEY, apiKey)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val itemResponse = response?.getJSONArray(RESULTS)
                    list.clear()
                    try {
                        itemResponse?.run {
                            for (i in 0 until itemResponse.length()) {
                                val filmItems = FilmResponse()
                                val value = itemResponse.getJSONObject(i)
                                filmItems.filmId = value.getString(ID)
                                filmItems.posterUrl =
                                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + value.getString(
                                        POSTER_PATH
                                    )
                                list.add(filmItems)
                            }
                            callback.onPopularMovieResponses(list)
                        }
                    } catch (e: JSONException) {
                        Log.i(TAG, "fun popularMovieRequest, onResponse, catch JsonException")
                        Log.e(TAG, e.printStackTrace().toString())
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i(TAG, "fun popularMovieRequest, onError: ${anError?.errorCode.toString()}")
                }
            })
    }

    fun movieDetailsRequest(movieId: String, callback: MovieDetailsContract) {
        val filmItems = MovieDetailsResponse()
        AndroidNetworking.get(movieDetailUrl)
            .addPathParameter(MOVIE_ID, movieId)
            .addPathParameter(API_KEY, apiKey)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    response?.let {
                        filmItems.movieTitle = response.getString(TITLE)
                        filmItems.language = languageProcessing(response.getString(LANGUAGE))
                        filmItems.overview = response.getString(OVERVIEW)
                        filmItems.runtime = runtimeProcessing(response.getString(RUNTIME))
                        filmItems.status = response.getString(STATUS)
                        filmItems.posterUrl =
                            "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + response.getString(
                                POSTER_PATH
                            )
                        callback.onMovieDetailsResponses(filmItems)
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i(TAG, "fun movieDetailsRequest, onError: ${anError?.errorCode.toString()}")
                }
            })
    }

    fun movieCreditsRequest(movieId: String, callback: MovieCreditsContract){
        val list = ArrayList<MovieCreditsResponse>()
        AndroidNetworking.get(movieCreditUrl)
            .addPathParameter(MOVIE_ID, movieId)
            .addPathParameter(API_KEY, apiKey)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val itemResponse = response?.getJSONArray(CAST)
                    list.clear()
                    try {
                        itemResponse?.run {
                            for (i in 0 until 8) {
                                val filmItems = MovieCreditsResponse()
                                val value = itemResponse.getJSONObject(i)
                                filmItems.artistName = value.getString(NAME)
                                filmItems.castPosition = value.getString(CHARACTER)
                                filmItems.photoUrl =
                                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + value.getString(
                                        PROFILE_PATH
                                    )
                                list.add(filmItems)
                            }
                            callback.onMovieCastsResponse(list)
                        }
                    } catch (e: JSONException) {
                        Log.i(TAG, "fun movieCreditsRequest, onResponse, catch JsonException")
                        Log.e(TAG, e.printStackTrace().toString())
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i(TAG, "fun movieCreditsRequest, onError: ${anError?.errorCode.toString()}")
                }
            })
    }

    fun popularTvShowRequest(callback: PopularTvShowContract) {
        val list = ArrayList<FilmResponse>()
        AndroidNetworking.get(popularTvShowUrl)
            .addPathParameter(API_KEY, apiKey)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val itemResponse = response?.getJSONArray(RESULTS)
                    list.clear()
                    try {
                        itemResponse?.run {
                            for (i in 0 until itemResponse.length()) {
                                val filmItems = FilmResponse()
                                val value = itemResponse.getJSONObject(i)
                                filmItems.filmId = value.getString(ID)
                                filmItems.posterUrl =
                                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + value.getString(
                                        POSTER_PATH
                                    )
                                list.add(filmItems)
                            }
                            callback.onPopularTvShowResponses(list)
                        }
                    } catch (e: JSONException) {
                        Log.i(TAG, "fun getPopularTvShowList, onResponse, catch JsonException")
                        Log.e(TAG, e.printStackTrace().toString())
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i(
                        TAG,
                        "fun getPopularTvShowList, onError: ${anError?.errorCode.toString()}"
                    )
                }
            })
    }

    fun tvShowDetailsRequest(tvShowId: String, callback: TvShowDetailsContract) {
        val filmItems = TvShowDetailsResponse()
        AndroidNetworking.get(tvShowDetailUrl)
            .addPathParameter(TV_ID, tvShowId)
            .addPathParameter(API_KEY, apiKey)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    response?.let {
                        filmItems.tvShowName = response.getString(NAME)
                        filmItems.language = languageProcessing(response.getString(LANGUAGE))
                        filmItems.overview = response.getString(OVERVIEW)
                        filmItems.epsRuntime = runtimeProcessing(
                            response.getJSONArray(EPS_RUNTIME).optString(0).toString()
                        )
                        filmItems.status = response.getString(STATUS)
                        filmItems.posterUrl =
                            "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + response.getString(
                                POSTER_PATH
                            )
                        callback.onTvShowDetailsResponses(filmItems)
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i(TAG, "fun getTvShowDetails, onError: ${anError?.errorCode.toString()}")
                }
            })
    }

    fun tvShowCreditsRequest(movieId: String, callback: TvShowCreditsContract){
        val list = ArrayList<TvShowCreditsResponse>()
        AndroidNetworking.get(tvShowCreditUrl)
            .addPathParameter(TV_ID, movieId)
            .addPathParameter(API_KEY, apiKey)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val itemResponse = response?.getJSONArray(CAST)
                    list.clear()
                    try {
                        itemResponse?.run {
                            for (i in 0 until 8) {
                                val filmItems = TvShowCreditsResponse()
                                val value = itemResponse.getJSONObject(i)
                                filmItems.artistName = value.getString(NAME)
                                filmItems.castPosition = value.getString(CHARACTER)
                                filmItems.photoUrl =
                                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + value.getString(
                                        PROFILE_PATH
                                    )
                                list.add(filmItems)
                            }
                            callback.onTvShowCastsResponse(list)
                        }
                    } catch (e: JSONException) {
                        Log.i(TAG, "fun tvShowCreditsRequest, onResponse, catch JsonException")
                        Log.e(TAG, e.printStackTrace().toString())
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i(TAG, "fun tvShowCreditsRequest, onError: ${anError?.errorCode.toString()}")
                }
            })
    }

    fun languageProcessing(lang: String): String {
        return when (lang) {
            "en" -> {
                "English"
            }
            "ja" -> {
                "Japanese"
            }
            else -> {
                lang
            }
        }
    }

    fun runtimeProcessing(rtm: String): String {
        val time: Int = rtm.toInt()
        val hour = time / 60
        val minute = time % 60
        return "$hour h $minute m"
    }

    interface PopularMovieContract {
        fun onPopularMovieResponses(popularMovie: List<FilmResponse>)
    }

    interface MovieDetailsContract {
        fun onMovieDetailsResponses(movieDetails: MovieDetailsResponse)
    }

    interface MovieCreditsContract {
        fun onMovieCastsResponse(movieCredits: List<MovieCreditsResponse>)
    }

    interface PopularTvShowContract {
        fun onPopularTvShowResponses(popularTvShow: List<FilmResponse>)
    }

    interface TvShowDetailsContract {
        fun onTvShowDetailsResponses(tvShowDetails: TvShowDetailsResponse)
    }

    interface TvShowCreditsContract {
        fun onTvShowCastsResponse(tvShowCredits: List<TvShowCreditsResponse>)
    }
}