package com.example.jetquiz.network

import com.example.jetquiz.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionsAPI {

    @GET("world.json")
    suspend fun getAllQuestions(): Question

}