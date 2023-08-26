package com.example.jetquiz.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetquiz.R
import com.example.jetquiz.components.NextButton
import com.example.jetquiz.navigation.QuizScreens
import com.example.jetquiz.util.AppColors

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun ResultScreen(
    viewModel: QuestionsViewModel,
    navController: NavController
) {
    var percentSolved =
        viewModel.skipped.value/((viewModel.quizTotalNumberOfQuestion.value).toFloat())
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Good Job!",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                color = Color.Black) })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(AppColors.Pink)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.result),
                            contentDescription = "award",
                            modifier = Modifier.fillMaxHeight(0.6f))
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = "You got +${viewModel.correctAnswers.value*10} quiz points",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge)
                    }
                }
                Divider(
                    thickness = 2.dp,
                    color = Color.LightGray
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(0.3f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ResultSection(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(),
                        topLabel = "CORRECT ANSWER",
                        topValue = "${viewModel.correctAnswers.value} questions",
                        bottomLabel = "SKIPPED",
                        bottomValue = "${viewModel.skipped.value}")
                    ResultSection(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(),
                        topLabel = "COMPLETION",
                        topValue = "${
                            ((1-percentSolved)*100).toInt()
                        }%",
                        bottomLabel = "INCORRECT ANSWER",
                        bottomValue = "${viewModel.inCorrectAnswers.value}")
                }
                NextButton(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    text = "DONE"
                ) {
                    viewModel.resetQuiz()
                    navController.navigate(QuizScreens.CategoryScreen.name) {
                        popUpTo(route = QuizScreens.SplashScreen.name) {
                            inclusive = true
                        }
                    }
                }

            }
        }
        BackHandler(
            enabled = true
        ) {}
    }
}

@Composable
fun ResultSection(
    modifier: Modifier,
    topLabel: String,
    topValue: String,
    bottomLabel: String,
    bottomValue: String
) {
    Column(
        modifier = modifier
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = topLabel,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray)

        Text(text = topValue,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = bottomLabel,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray)

        Text(text = bottomValue,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black)
    }
}