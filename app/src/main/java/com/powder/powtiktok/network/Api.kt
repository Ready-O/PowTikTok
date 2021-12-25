package com.powder.powtiktok.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object Api {

    private const val BASE_URL = "https://firebasestorage.googleapis.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val videoService: VideoApiService by lazy {retrofit.create(VideoApiService::class.java)}
}

interface VideoApiService {
    @GET("v0/b/powder-c9282.appspot.com/o/test%2Fstatic_feed.json?alt=media&token=c5bbde3a-129b-449e-a79e-d2a0ccffbd0f")
    suspend fun getVideos() : List<Video>
}