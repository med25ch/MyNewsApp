package com.example.mynewsapp.models

import com.example.mynewsapp.R

data class NewsData(val id:Int, val image:Int = R.drawable.breaking_news, val author:String, val title:String, val description:String, val publishedAt:String)

