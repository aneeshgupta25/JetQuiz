package com.example.jetquiz.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetquiz.screens.QuestionsViewModel
import com.example.jetquiz.screens.QuizHome

@Composable
fun QuizApp() {
    val viewmodel: QuestionsViewModel = hiltViewModel()
    QuizHome(viewModel = viewmodel)
}