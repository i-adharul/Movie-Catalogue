package com.adharul.i.moviecatalogue.datalocal.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowCreditsEntity(
    var artistName: String? = null,
    var castPosition: String? = null,
    var photoUrl: String? = null
) : Parcelable