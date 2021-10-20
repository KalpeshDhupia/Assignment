package com.example.assignment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotosModel(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("pages")
    val pages: Int? = null,

    @field:SerializedName("perpage")
    val perpage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("photo")
    val photo: List<PhotoModel?>? = null
) : Parcelable
