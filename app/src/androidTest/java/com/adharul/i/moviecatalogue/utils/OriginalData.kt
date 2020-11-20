package com.adharul.i.moviecatalogue.utils

import com.adharul.i.moviecatalogue.datalocal.entity.MovieEntity
import com.adharul.i.moviecatalogue.datalocal.entity.TvShowEntity

object OriginalData {
    fun generateMovieDetails(): MovieEntity {
        return MovieEntity(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "Released",
            "Japanese",
            "1 h 57 m"
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
}