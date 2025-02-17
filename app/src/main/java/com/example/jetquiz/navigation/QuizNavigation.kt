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
import com.example.jetquiz.screens.LoadingPlaygroundScreen
import com.example.jetquiz.screens.QuestionsViewModel
import com.example.jetquiz.screens.QuizPlaygroundScreen
import com.example.jetquiz.screens.ResultScreen
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
                delay(1000)
                navController.navigate(QuizScreens.CategoryScreen.name) {
                    popUpTo(route = QuizScreens.SplashScreen.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = QuizScreens.CategoryScreen.name) {
            CategoryScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = QuizScreens.DifficultyLevelScreen.name) {
            DifficultyLevelScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = QuizScreens.ConfirmationScreen.name) {
            ConfirmationScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = QuizScreens.LoadingPlaygroundScreen.name) {
            LoadingPlaygroundScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = QuizScreens.QuizPlaygroundScreen.name) {
            QuizPlaygroundScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = QuizScreens.ResultScreen.name) {
            ResultScreen(viewModel = viewModel, navController = navController)
        }

    }
}