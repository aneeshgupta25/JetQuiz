package com.example.jetquiz.screens

import android.util.Log
import androidx.compose.runtime.Composable

@Composable
fun QuizHome(viewModel: QuestionsViewModel) {

    val questions = viewModel.data.value.data?.results?.toMutableList()
    if(viewModel.data.value.loading == true) {
        Log.d("Loading", "JetNotesHome: Loading")
    } else {
        Log.d("Loading", "JetNotesHome: Loading Stopped")
        questions?.forEach {
            Log.d("Result", "JetNotesHome: ${it.question}")
        }
    }
}