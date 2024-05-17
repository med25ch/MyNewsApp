package com.example.mynewsapp.retrofit

import com.example.mynewsapp.models.ArticlesResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country : String, @Query("apiKey") apiKey : String
    ) : Call<ArticlesResult>

    @GET("top-headlines")
    suspend fun getTopHeadlinesByCategory(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String,
        @Query("category") category : String,
    ) : Call<ArticlesResult>
}