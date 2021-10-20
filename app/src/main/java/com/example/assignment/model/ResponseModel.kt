package com.example.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseModel(
    @field:SerializedName("photos")
    val photos: PhotosModel? = null,

    @field:SerializedName("stat")
    val stat: String? = null
): Parcelable
