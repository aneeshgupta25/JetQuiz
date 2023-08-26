package com.example.jetquiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetquiz.R
import com.example.jetquiz.components.NextButton
import com.example.jetquiz.navigation.QuizScreens
import com.example.jetquiz.util.AppColors
import com.example.jetquiz.util.ScreenConfig

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun ConfirmationScreen(
    viewModel: QuestionsViewModel,
    navController: NavController
) {

    val quizTheme = viewModel.getQuizTheme()
    val quizLevel = viewModel.getQuizLevel()
    val screenHeight = ScreenConfig.getHeight()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Quiz Details",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center)
            },
                modifier = Modifier.padding(horizontal = 10.dp),
                navigationIcon = {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Navigate back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        tint = Color.White)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(AppColors.Purple))
        },
        containerColor = AppColors.Purple
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.confirm_quiz),
                    contentDescription = "",
                    modifier = Modifier.height(screenHeight/3))
                Card(
                    modifier = Modifier
                        .height(screenHeight / 3 * 2)
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 15.dp),
                    shape = RoundedCornerShape(corner = CornerSize(25.dp)),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp).fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = quizTheme.uppercase(),
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray)
                        Text(text = "$quizLevel Jet Quiz",
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            shape = RoundedCornerShape(corner = CornerSize(25.dp)),
                            colors = CardDefaults.cardColors(AppColors.VeryLightPurple)
                        ) {
                            Column {
                                Card(
                                    modifier = Modifier
                                        .height(60.dp)
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                    colors = CardDefaults.cardColors(Color.Transparent)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(horizontal = 10.dp)
                                    ) {
                                        ConfirmQuizIcon(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .weight(0.2f),
                                            imageVector = Icons.Default.Remove) {
                                            viewModel.deleteQuestion()
                                        }
                                        Text(text = "${viewModel.quizTotalNumberOfQuestion.value} questions",
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.SemiBold,
                                            style = MaterialTheme.typography.titleLarge,
                                            modifier = Modifier.weight(0.6f))
                                        ConfirmQuizIcon(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .weight(0.2f),
                                            imageVector = Icons.Default.Add) {
                                            viewModel.addQuestion()
                                        }
                                    }
                                }
                                Divider(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    thickness = 2.dp,
                                    color = Color.Gray)
                                Text("Each question carries 10 points",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium)
//                                Card(
//                                    modifier = Modifier
//                                        .height(60.dp)
//                                        .fillMaxWidth()
//                                        .padding(4.dp),
//                                    colors = CardDefaults.cardColors(Color.Transparent)
//                                ) {
//
//                                    Row(
//                                        horizontalArrangement = Arrangement.Center,
//                                        verticalAlignment = Alignment.CenterVertically,
//                                        modifier = Modifier
//                                            .fillMaxHeight()
//                                            .fillMaxWidth()
//                                            .padding(horizontal = 10.dp)
//                                    ) {
//                                        ConfirmQuizIcon(
//                                            modifier = Modifier
//                                                .size(50.dp),
//                                            imageVector = Icons.Default.LocalFlorist,
//                                            color = AppColors.Pink)
//                                        Text(text = "+$points points",
//                                            textAlign = TextAlign.Center,
//                                            fontWeight = FontWeight.SemiBold,
//                                            style = MaterialTheme.typography.titleLarge)
//                                    }
//                                }
                            }
                        }
                        Text(text = "Description",
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray)
                        Text(text = "Any time is a good time for a quiz and even better if that happens to be a $quizTheme themed quiz!!",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black)
                        Spacer(modifier = Modifier.weight(1f))
                        NextButton(
                            modifier = Modifier.fillMaxWidth(0.6f),
                            text = "PLAY!!"
                        ) {
                            navController.navigate(QuizScreens.LoadingPlaygroundScreen.name)
                            viewModel.getAllQuestions()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmQuizIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    color: Color = AppColors.Pink,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Surface(
            modifier = modifier,
            shape = CircleShape,
            color = color
        ) {
            Icon(imageVector = imageVector, contentDescription = "Icon",
                modifier = Modifier.padding(5.dp),
                tint = Color.White)
        }
    }
}