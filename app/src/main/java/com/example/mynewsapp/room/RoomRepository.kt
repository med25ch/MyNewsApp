package com.example.mynewsapp.room

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface Repository {

    suspend fun insert(articleEntity: ArticleEntity)

    suspend fun delete(articleEntity: ArticleEntity)

    suspend fun update(articleEntity: ArticleEntity)

    suspend fun getAllFavoriteArticles(): Flow<List<ArticleEntity>>

    suspend fun getArticle(id: Int): Flow<ArticleEntity>
}




class RoomRepositoryImpl @Inject constructor(
    private val articlesDAO: ArticlesDAO
) : Repository{
    override suspend fun insert(articleEntity: ArticleEntity) {
        withContext(IO){
            articlesDAO.insert(articleEntity)
        }
    }

    override suspend fun delete(articleEntity: ArticleEntity) {
        withContext(IO){
            articlesDAO.delete(articleEntity)
        }
    }

    override suspend fun update(articleEntity: ArticleEntity) {
        withContext(IO){
            articlesDAO.update(articleEntity)
        }
    }

    override suspend fun getAllFavoriteArticles(): Flow<List<ArticleEntity>> {
        return withContext(IO){
            articlesDAO.getAllFavoriteArticles()
        }
    }

    override suspend fun getArticle(id: Int): Flow<ArticleEntity> {
        return  withContext(IO){
            articlesDAO.getArticle(id)
        }
    }
}