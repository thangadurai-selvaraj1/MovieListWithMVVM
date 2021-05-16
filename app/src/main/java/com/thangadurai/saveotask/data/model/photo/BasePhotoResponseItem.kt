package com.thangadurai.saveotask.data.model.photo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BasePhotoResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) : Parcelable