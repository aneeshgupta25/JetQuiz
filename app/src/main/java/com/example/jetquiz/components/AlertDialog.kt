package com.example.jetquiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.jetquiz.util.AppColors

@Preview
@Composable
fun AlertDialog(
    onDismissRequest: ()->Unit = {},
    onNegativeRequest: ()->Unit = {},
    onPositiveRequest: ()->Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismissRequest.invoke() },
    ) {
        Card(
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            colors = CardDefaults.cardColors(AppColors.Purple)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = AppColors.Purple)
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //icon
                //save changes?
                //buttons
                Icon(imageVector = Icons.Default.Info, contentDescription = "Attention",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Exit the Quiz ?",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = "Your progress will be lost!",
                    color = Color(0xFFCFCFCF),
                    style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Button(onClick = { onNegativeRequest.invoke() },
                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFF0000))) {
                        Text("Exit")
                    }
                    Button(onClick = { onPositiveRequest.invoke() },
                        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                        colors = ButtonDefaults.buttonColors(Color(0xFF30BE71))) {
                        Text("Resume")
                    }
                }
            }
        }
    }
}