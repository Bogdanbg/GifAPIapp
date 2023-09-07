package com.example.gifapi.data.network

import com.example.gifapi.data.GifResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("v1/gifs/trending")
    fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<GifResponse>
}