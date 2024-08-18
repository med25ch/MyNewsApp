package com.example.mynewsapp.repositories

import android.util.Log
import com.example.mynewsapp.retrofit.ApiHelper
import com.example.mynewsapp.retrofit.ArticlesResult
import com.example.mynewsapp.retrofit.INewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsArticlesRepo @Inject constructor(
    private val newsApi: INewsApi
) : ApiHelper{

    private val KEY = "4247013acfde47c0bacb38754bdfdebd"

    override fun getTopHeadlines(country: COUNTRY) = flow {
        try {
            emit(newsApi.getTopHeadlines(country.name.lowercase(), KEY))
        }catch (e : Exception) {
            Log.e("NewsArticlesRepo - getTopHeadlines","${e.cause}  | ${e.message}")
        }
    }

    override fun getTopHeadlinesByCategory(
        country: COUNTRY,
        category: CATEGORY
    ): Flow<ArticlesResult> = flow {
        try {
            emit(newsApi.getTopHeadlinesByCategory(country.name.lowercase(), KEY, category.name.lowercase()))
        }catch (e : Exception) {
            Log.e("NewsArticlesRepo - getTopHeadlinesByCategory","${e.cause}  | ${e.message}")
        }
    }

}

enum class CATEGORY {
    Business,
    Entertainment,
    Health,
    Science,
    Sports,
    Technology
}

enum class COUNTRY {
    AR,
    CA,
    FR,
    IT,
    JP,
    MA,
    RU,
    SA,
    US
}