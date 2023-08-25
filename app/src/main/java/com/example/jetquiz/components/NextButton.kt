package com.example.jetquiz.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetquiz.util.AppColors

@Composable
fun NextButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String = "NEXT",
    enabled: Boolean = true,
    onClick: ()->Unit = {}
) {
    Button(
        modifier = modifier
            .height(60.dp),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.Purple,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}