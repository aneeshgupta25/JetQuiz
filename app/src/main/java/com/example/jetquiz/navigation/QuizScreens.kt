package com.example.jetquiz.navigation

enum class QuizScreens {
    SplashScreen,
    CategoryScreen,
    DifficultyLevelScreen,
    ConfirmationScreen,
    LoadingPlaygroundScreen,
    QuizPlaygroundScreen,
    ResultScreen;

    companion object {
        fun fromRoute(route: String?): QuizScreens
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            CategoryScreen.name -> CategoryScreen
            DifficultyLevelScreen.name -> DifficultyLevelScreen
            ConfirmationScreen.name -> ConfirmationScreen
            LoadingPlaygroundScreen.name -> LoadingPlaygroundScreen
            QuizPlaygroundScreen.name -> QuizPlaygroundScreen
            ResultScreen.name -> ResultScreen
            null -> CategoryScreen
            else -> throw IllegalArgumentException("Route $route not recognised")
        }
    }
}