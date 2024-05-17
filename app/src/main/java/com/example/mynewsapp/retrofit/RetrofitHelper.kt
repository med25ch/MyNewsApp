package com.example.mynewsapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://newsapi.org/v2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

    val retrofitService : INewsApi by lazy {
        retrofit.create(INewsApi::class.java)
    }

//    fun getInstance(): Retrofit {
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            // we need to add converter factory to
//            // convert JSON object to Java object
//            .build()
//    }

}