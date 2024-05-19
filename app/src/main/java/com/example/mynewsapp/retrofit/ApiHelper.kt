package com.example.mynewsapp.retrofit

import com.example.mynewsapp.repositories.CATEGORY
import com.example.mynewsapp.repositories.COUNTRY
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getTopHeadlines(country: COUNTRY) : Flow<ArticlesResult>
    fun getTopHeadlinesByCategory(country: COUNTRY, category: CATEGORY) : Flow<ArticlesResult>
}