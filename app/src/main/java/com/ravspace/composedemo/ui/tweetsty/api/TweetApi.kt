package com.ravspace.composedemo.ui.tweetsty.api

import com.ravspace.composedemo.ui.tweetsty.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetApi {



    @GET("/v3/b/66150baae41b4d34e4e18eed?meta=false")
    suspend fun getTweet(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/66150baae41b4d34e4e18eed?meta=false")
    @Headers("X-JSON-Path: tweet..category")
    suspend fun getCategory(): Response<List<String>>
}