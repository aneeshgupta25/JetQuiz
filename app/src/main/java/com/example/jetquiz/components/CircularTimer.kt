package com.example.jetquiz.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetquiz.util.AppColors
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun CircularTimer(
    modifier: Modifier = Modifier,
    progress: Float = 0.2f,
    timerCount: Int = 0,
    color: Color = AppColors.Pink,
    backgroundColor: Color = AppColors.LightPink,
    strokeWidth: Dp = 8.dp
) {
    Surface(
        modifier = Modifier.size(120.dp),
        color = Color.White
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = 120f

            // background circle
            drawCircle(
                color = backgroundColor,
                radius = radius,
                center = Offset(centerX, centerY),
            )

            // timer arc
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = progress * 360,
                useCenter = true,
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2, radius * 2),
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("$timerCount",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun CircularTimerScreen(
    onTimerFinished: ()->Unit = {}
) {
    var timerProgress by remember { mutableStateOf(0f) }
    var timerCount by remember { mutableStateOf(20) }
    LaunchedEffect(true) {
        delay(1000)
        while (timerProgress < 1f) {
            timerProgress += 0.05f
            timerCount -= 1
            delay(1000) // Adjust delay to change the speed of progress
        }
        if(timerProgress == 1f) onTimerFinished.invoke()
    }

    CircularTimer(progress = timerProgress, timerCount = timerCount)
}