package com.example.mynewsapp.retrofit

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source")
    var source: Source? = Source(),
    @SerializedName("author") var author: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("urlToImage") var urlToImage: String? = null,
    @SerializedName("publishedAt") var publishedAt: String? = null,
    @SerializedName("content") var content: String? = null
)

data class Source(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null
)

data class ArticlesResult(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int? = null,
    @SerializedName("articles")
    var articles: List<Article> = listOf()
)