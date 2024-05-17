package com.example.mynewsapp.repositories

import com.example.mynewsapp.retrofit.INewsApi
import javax.inject.Inject

class NewsArticlesRepo @Inject constructor(
    private val newsApi: INewsApi
) {

    private val KEY = "4247013acfde47c0bacb38754bdfdebd"
    suspend fun getArticlesByCountry(country: COUNTRY) = newsApi.getTopHeadlines(country.name, KEY)

    suspend fun getTopHeadlinesByCategory(country: COUNTRY, category: CATEGORY) =
        newsApi.getTopHeadlinesByCategory(country.name, KEY, category.name)

}

enum class CATEGORY {
    BUSINESS,
    ENTERTAINMENT,
    GENERAL,
    HEALTH,
    SCIENCE,
    SPORTS,
    TECHNOLOGY
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