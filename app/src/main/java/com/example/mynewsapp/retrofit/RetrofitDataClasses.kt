package com.example.mynewsapp.retrofit

import com.example.mynewsapp.room.ArticleEntity
import com.example.mynewsapp.room.TemporaryArticleEntity
import com.google.gson.annotations.SerializedName
import retrofit2.Response

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
    var status: String ?= null,
    @SerializedName("totalResults")
    var totalResults: Int? = null,
    @SerializedName("articles")
    var articles: List<Article> = listOf()
)

data class Status(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("message")
    var message: String? = null
)

fun Article.toArticleEntity()  : ArticleEntity {
    return ArticleEntity(
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: "",
        publishedAt = this.publishedAt ?: "",
        content = this.content ?: ""
    )
}

fun Article.toTemporaryArticleEntity()  : TemporaryArticleEntity {
    return TemporaryArticleEntity(
        author = this.author ?: "",
        title = this.title ?: "",
        description = this.description ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: "",
        publishedAt = this.publishedAt ?: "",
        content = this.content ?: ""
    )
}