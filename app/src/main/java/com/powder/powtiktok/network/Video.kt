package com.powder.powtiktok.network

import com.squareup.moshi.Json

data class Video(
    val id: Int,
    @Json(name = "video_url") val videoUrl: String,
    val title: String,
    val game: String,
    val author: String
)
