package com.adharul.i.moviecatalogue.datalocal.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmEntity(
    var filmId: String? = null,
    var posterUrl: String? = null
) : Parcelable