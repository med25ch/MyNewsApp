package com.example.mynewsapp.room

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface Repository {

    suspend fun insert(articleEntity: ArticleEntity)

    suspend fun insertTemporaryArticle(temporaryArticleEntity: TemporaryArticleEntity)

    suspend fun delete(articleEntity: ArticleEntity)

    suspend fun update(articleEntity: ArticleEntity)

    suspend fun getAllFavoriteArticles(): Flow<List<ArticleEntity>>

    suspend fun getArticle(id: Int): Flow<ArticleEntity>

    suspend fun deleteAllArticle()

    suspend fun deleteAllTemporaryArticle()

    suspend fun getTemporaryArticle(): Flow<TemporaryArticleEntity>
}




class RoomRepositoryImpl @Inject constructor(
    private val articlesDAO: ArticlesDAO
) : Repository{
    override suspend fun insert(articleEntity: ArticleEntity) {
        withContext(IO){
            articlesDAO.insert(articleEntity)
        }
    }

    override suspend fun insertTemporaryArticle(temporaryArticleEntity: TemporaryArticleEntity) {
        withContext(IO){
            articlesDAO.insertTemporaryArticle(temporaryArticleEntity)
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

    override suspend fun deleteAllArticle() {
        return  withContext(IO){
            articlesDAO.deleteAllItems()
        }
    }

    override suspend fun deleteAllTemporaryArticle() {
        return  withContext(IO){
            articlesDAO.deleteAllTemporaryItems()
        }
    }

    override suspend fun getTemporaryArticle(): Flow<TemporaryArticleEntity> {
        return  withContext(IO){
            articlesDAO.getTemporaryArticle()
        }
    }
}