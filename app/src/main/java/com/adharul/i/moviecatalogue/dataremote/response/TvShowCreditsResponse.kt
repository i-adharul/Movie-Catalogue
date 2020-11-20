package com.adharul.i.moviecatalogue.dataremote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowCreditsResponse(
    var artistName: String? = null,
    var castPosition: String? = null,
    var photoUrl: String? = null
) : Parcelable