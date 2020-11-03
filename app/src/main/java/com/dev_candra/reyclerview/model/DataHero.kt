package com.dev_candra.reyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataHero (
    var name : String,
    var deskripsi : String,
    var photo : String
) : Parcelable