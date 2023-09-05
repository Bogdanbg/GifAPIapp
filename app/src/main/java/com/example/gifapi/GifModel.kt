package com.example.gifapi

import com.google.gson.annotations.SerializedName

data class GifModel(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)
