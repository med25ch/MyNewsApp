package com.example.mynewsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ArticleEntity::class,TemporaryArticleEntity::class], version = 1)
abstract class Database : RoomDatabase(){
    abstract val articlesDAO : ArticlesDAO
}