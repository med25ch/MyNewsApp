package com.example.mynewsapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: ArticleEntity)

    @Delete
    suspend fun delete(articleEntity: ArticleEntity)

    @Update
    suspend fun update(articleEntity: ArticleEntity)

    @Query("SELECT * FROM articles")
    fun getAllFavoriteArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * from articles WHERE id = :id")
    fun getArticle(id: Int): Flow<ArticleEntity>

    @Query("DELETE FROM articles")
    fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemporaryArticle(temporaryArticleEntity: TemporaryArticleEntity)

    @Query("SELECT * FROM temporary_articles LIMIT 1")
    fun getTemporaryArticle(): Flow<TemporaryArticleEntity>

    @Query("DELETE FROM temporary_articles")
    suspend fun deleteAllTemporaryItems()
}