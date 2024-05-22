package com.example.mynewsapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynewsapp.retrofit.Source
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String = "",
    var publishedAt: String = "",
    var content: String = ""
)
