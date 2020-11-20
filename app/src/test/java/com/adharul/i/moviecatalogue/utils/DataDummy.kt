package com.adharul.i.moviecatalogue.utils

import com.adharul.i.moviecatalogue.datalocal.entity.FilmEntity
import com.adharul.i.moviecatalogue.datalocal.entity.MovieEntity
import com.adharul.i.moviecatalogue.datalocal.entity.TvShowEntity
import com.adharul.i.moviecatalogue.dataremote.response.FilmResponse
import com.adharul.i.moviecatalogue.dataremote.response.MovieDetailsResponse
import com.adharul.i.moviecatalogue.dataremote.response.TvShowDetailsResponse

object DataDummy {
    fun generatePopularMovie(): List<FilmEntity> {
        val movies = ArrayList<FilmEntity>()
        movies.add(
            FilmEntity(
                "724989",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "635302",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "531219",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "528085",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "741074",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/hddzYJtfYYeMDOQVcH58n8m1W3A.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "400160",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "741067",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "740985",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "560050",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg"
            )
        )
        movies.add(
            FilmEntity(
                "497582",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/riYInlsq2kf1AWoGm80JQW5dLKp.jpg"
            )
        )
        return movies
    }

    fun generatePopularTvShow(): List<FilmEntity> {
        val tvShows = ArrayList<FilmEntity>()
        tvShows.add(
            FilmEntity(
                "71712",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "82856",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "62286",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "63174",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "76479",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "87739",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "94305",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/z31GxpVgDsFAF4paZR8PRsiP16D.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "1416",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "456",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg"
            )
        )
        tvShows.add(
            FilmEntity(
                "48866",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg"
            )
        )
        return tvShows
    }

    fun generateMovieDetails(): MovieEntity {
        return MovieEntity(
            "Hard Kill",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
            "Released",
            "English",
            "1 h 38 m"
        )
    }

    fun generateTvShowDetails(): TvShowEntity {
        return TvShowEntity(
            "The Good Doctor",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
            "Returning Series",
            "English",
            "0 h 42 m"
        )
    }

    fun generateRemotePopularMovie(): List<FilmResponse> {
        val movies = ArrayList<FilmResponse>()
        movies.add(
            FilmResponse(
                "724989",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "635302",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "531219",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "528085",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "741074",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/hddzYJtfYYeMDOQVcH58n8m1W3A.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "400160",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "741067",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "740985",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "560050",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg"
            )
        )
        movies.add(
            FilmResponse(
                "497582",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/riYInlsq2kf1AWoGm80JQW5dLKp.jpg"
            )
        )
        return movies
    }

    fun generateRemotePopularTvShow(): List<FilmResponse> {
        val tvShows = ArrayList<FilmResponse>()
        tvShows.add(
            FilmResponse(
                "71712",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "82856",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "62286",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "63174",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "76479",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "87739",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "94305",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/z31GxpVgDsFAF4paZR8PRsiP16D.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "1416",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "456",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg"
            )
        )
        tvShows.add(
            FilmResponse(
                "48866",
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg"
            )
        )
        return tvShows
    }

    fun generateRemoteMovieDetails(): MovieDetailsResponse {
        return MovieDetailsResponse(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "Released",
            "Japanese",
            "1 h 57 m"
        )
    }

    fun generateRemoteTvShowDetails(): TvShowDetailsResponse {
        return TvShowDetailsResponse(
            "The Good Doctor",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
            "Returning Series",
            "English",
            "0 h 42 m"
        )
    }
}