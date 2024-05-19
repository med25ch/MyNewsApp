package com.example.mynewsapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(@Query("country") country : String, @Query("apiKey") apiKey : String
    ) : ArticlesResult

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlinesByCategory(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String,
        @Query("category") category : String,
    ) : ArticlesResult

}