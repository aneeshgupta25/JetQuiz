package com.example.jetquiz.navigation

enum class QuizScreens {
    SplashScreen,
    CategoryScreen,
    DifficultyLevelScreen,
    ConfirmationScreen,
    QuizPlaygroundScreen;

    companion object {
        fun fromRoute(route: String?): QuizScreens
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            CategoryScreen.name -> CategoryScreen
            DifficultyLevelScreen.name -> DifficultyLevelScreen
            ConfirmationScreen.name -> ConfirmationScreen
            QuizPlaygroundScreen.name -> QuizPlaygroundScreen
            null -> CategoryScreen
            else -> throw IllegalArgumentException("Route $route not recognised")
        }
    }
}