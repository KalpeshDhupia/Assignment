package com.example.assignment.model

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("owner")
    val owner: String? = null,

    @field:SerializedName("secret")
    val secret: String? = null,

    @field:SerializedName("server")
    val server: String? = null,

    @field:SerializedName("farm")
    val farm: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("ispublic")
    val ispublic: Int? = null,

    @field:SerializedName("isfriend")
    val isfriend: Int? = null,

    @field:SerializedName("isfamily")
    val isfamily: Int? = null
)
