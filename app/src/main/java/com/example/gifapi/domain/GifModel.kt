package com.example.gifapi.domain

import com.google.gson.annotations.SerializedName

data class GifModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)
