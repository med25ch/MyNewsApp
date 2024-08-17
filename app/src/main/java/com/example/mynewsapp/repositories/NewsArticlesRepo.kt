package com.example.mynewsapp.repositories

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
        emit(newsApi.getTopHeadlines(country.name.lowercase(), KEY))
    }

    override fun getTopHeadlinesByCategory(
        country: COUNTRY,
        category: CATEGORY
    ): Flow<ArticlesResult> = flow {
        emit(newsApi.getTopHeadlinesByCategory(country.name.lowercase(), KEY, category.name.lowercase()))
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