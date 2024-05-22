package com.example.mynewsapp.di

import android.app.Application
import androidx.room.Room
import com.example.mynewsapp.Constants
import com.example.mynewsapp.retrofit.INewsApi
import com.example.mynewsapp.room.ArticlesDAO
import com.example.mynewsapp.room.Database
import com.example.mynewsapp.room.Repository
import com.example.mynewsapp.room.RoomRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                provideHttpLoggingInterceptor()
            )
            .build()
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): INewsApi =
        retrofit.create(INewsApi::class.java)


    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "MyDataBase"
        )
//            .addMigrations() later add migrations if u change the table
            .build()
    }

    @Provides
    @Singleton
    fun provideMyRepository(database:Database) : Repository {
        return RoomRepositoryImpl(database.articlesDAO)
    }

    @Provides
    fun provideChannelDao(database:Database): ArticlesDAO {
        return database.articlesDAO
    }

}