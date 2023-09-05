package com.example.gifapi

import java.util.Date

data class GifResponse(
    val data: List<GifModel>,
    val pagination: Pagination
)
