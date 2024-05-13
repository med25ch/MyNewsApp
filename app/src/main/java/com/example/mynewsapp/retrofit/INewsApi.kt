package com.example.mynewsapp.retrofit

import com.example.mynewsapp.models.ArticlesResult
import retrofit2.Response
import retrofit2.http.GET

interface INewsApi {

    @GET("/v2/top-headlines?country=us&apiKey=4247013acfde47c0bacb38754bdfdebd")
    suspend fun getTopHeadlines() : Response<ArticlesResult>
}