package com.example.jetquiz.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetquiz.navigation.QuizScreens
import com.example.jetquiz.util.AppColors

@Composable
fun LoadingPlaygroundScreen(
    viewModel: QuestionsViewModel,
    navController: NavController
) {
    if(viewModel.data.value.loading == false) {
        Log.d("HELLO", "LoadingPlaygroundScreen: navigated")
        navController.navigate(QuizScreens.QuizPlaygroundScreen.name)
    }
    if(viewModel.showToast.value) {
        Toast.makeText(LocalContext.current, "Something went wrong! Please try again!", Toast.LENGTH_SHORT).show()
        viewModel.dismissToast()
        navController.navigate(QuizScreens.CategoryScreen.name) {
            popUpTo(QuizScreens.CategoryScreen.name) {
                inclusive = true
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.Purple),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = AppColors.LightPink,
                strokeWidth = 5.dp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "Loading the Playground...",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )
        }
        BackHandler(
            enabled = true
        ) {}
    }
}