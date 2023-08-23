package com.example.jetquiz.model

data class Question(
    val response_code: Int,
    val results: List<QuestionItem>
)