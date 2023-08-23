package com.example.jetquiz.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ScreenConfig {

    @Composable
    fun getWidth(): Dp {
        return LocalConfiguration.current.screenWidthDp.dp
    }

    @Composable
    fun getHeight(): Dp {
        return LocalConfiguration.current.screenHeightDp.dp
    }

}