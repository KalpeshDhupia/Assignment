package com.example.assignment.pojo

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @field:SerializedName("photos")
    val photos: PhotosModel? = null,

    @field:SerializedName("stat")
    val stat: String? = null
)
