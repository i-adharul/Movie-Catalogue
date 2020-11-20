package com.adharul.i.moviecatalogue.dataremote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCreditsResponse(
    var artistName: String? = null,
    var castPosition: String? = null,
    var photoUrl: String? = null
) : Parcelable