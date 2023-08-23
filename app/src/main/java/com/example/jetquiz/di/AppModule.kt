package com.example.jetquiz.di

import com.example.jetquiz.network.QuestionsAPI
import com.example.jetquiz.repository.QuestionsRepository
import com.example.jetquiz.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionsRepository(api: QuestionsAPI): QuestionsRepository {
        return QuestionsRepository(api)
    }

    @Singleton
    @Provides
    fun provideQuestionAPI(): QuestionsAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionsAPI::class.java)
    }

}