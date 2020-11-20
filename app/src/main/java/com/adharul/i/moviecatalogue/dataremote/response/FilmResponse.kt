package com.adharul.i.moviecatalogue.dataremote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmResponse(
    var filmId: String? = null,
    var posterUrl: String? = null
) : Parcelable