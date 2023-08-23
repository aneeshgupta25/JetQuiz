package com.example.jetquiz.network

import com.example.jetquiz.model.Question
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface QuestionsAPI {

    @GET("api.php")
    suspend fun getAllQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String,
    ): Question

}