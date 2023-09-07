package com.example.gifapi.data

import com.example.gifapi.domain.GifModel

data class GifResponse(
    val data: List<GifModel>,
    val pagination: Pagination
)
