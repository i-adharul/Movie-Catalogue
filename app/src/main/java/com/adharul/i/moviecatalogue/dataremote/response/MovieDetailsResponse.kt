package com.adharul.i.moviecatalogue.dataremote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailsResponse(
    var movieTitle: String? = null,
    var posterUrl: String? = null,
    var overview: String? = null,
    var status: String? = null,
    var language: String? = null,
    var runtime: String? = null
) : Parcelable