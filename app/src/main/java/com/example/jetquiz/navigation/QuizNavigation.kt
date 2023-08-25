package com.example.jetquiz.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetquiz.screens.CategoryScreen
import com.example.jetquiz.screens.ConfirmationScreen
import com.example.jetquiz.screens.DifficultyLevelScreen
import com.example.jetquiz.screens.QuestionsViewModel
import com.example.jetquiz.screens.QuizPlaygroundScreen
import com.example.jetquiz.screens.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun QuizNavigation() {
    val navController = rememberNavController()
    val viewModel: QuestionsViewModel = viewModel()
    NavHost(navController = navController,
        startDestination = QuizScreens.SplashScreen.name) {

        composable(route = QuizScreens.SplashScreen.name) {
            SplashScreen()
            LaunchedEffect(true) {
                delay(3000)
                navController.navigate(QuizScreens.CategoryScreen.name)
            }
        }
        composable(route = QuizScreens.CategoryScreen.name) {
            CategoryScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = QuizScreens.DifficultyLevelScreen.name) {
            DifficultyLevelScreen()
        }
        composable(route = QuizScreens.ConfirmationScreen.name) {
            ConfirmationScreen()
        }
        composable(route = QuizScreens.QuizPlaygroundScreen.name) {
            QuizPlaygroundScreen()
        }

    }
}