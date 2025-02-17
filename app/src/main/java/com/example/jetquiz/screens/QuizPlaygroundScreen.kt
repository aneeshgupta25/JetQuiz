package com.example.jetquiz.screens

import android.text.Html
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.example.jetquiz.R
import com.example.jetquiz.components.AlertDialog
import com.example.jetquiz.components.CircularTimer
import com.example.jetquiz.components.CircularTimerScreen
import com.example.jetquiz.components.NextButton
import com.example.jetquiz.model.Question
import com.example.jetquiz.model.QuestionItem
import com.example.jetquiz.navigation.QuizScreens
import com.example.jetquiz.util.AppColors
import com.example.jetquiz.util.noRippleClickable

//@Preview
@Composable
fun QuizPlaygroundScreen(
    viewModel: QuestionsViewModel,
    navController: NavController,
) {

    PlaygroundLoaded(viewModel = viewModel, navController = navController)

//    if(viewModel.data.value.loading == true) {
//        LoadingPlayground()
//    } else {
//        PlaygroundLoaded(viewModel = viewModel,
//            navController = navController)
//    }
//    val questionsList = remember(questionItem) {
//        getQuestionsList(questionItem)
//    }
//    val questionsList = getQuestionsList(questionItem)
//    val radioButtonIndex = remember(questionItem) {
//        mutableStateOf<Int?>(null)
//    }
//
//    val correctAnswerState = remember {
//        mutableStateOf<Boolean?>(null)
//    }
//
//    val updateAnswer: (Int)->Unit = {
//        radioButtonIndex.value = it
//        correctAnswerState.value = questionsList[it] == questionItem.correct_answer
//    }
}

@Composable
fun NavigatingIcons(
    text: String,
    onClick: ()->Unit = {}
) {
    Card(
        modifier = Modifier.clickable{ onClick.invoke() },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        colors = CardDefaults.cardColors(Color(0xFF9087e5))
    ) {
        Text(text = text,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.White)
    }
}

@Composable
fun NumberOfQuestionsSolvedIndicator(modifier: Modifier,
                                     totalNumberOfQuestions: Int = 10,
                                     numberOfQuestionsCompleted: Int = 1) {
    Card(
        modifier = modifier.height(5.dp),
        colors = CardDefaults.cardColors(Color(0xFF9087e5))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(
                    numberOfQuestionsCompleted / (totalNumberOfQuestions).toFloat()
                )
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(Color.White)
        ) {}
    }
}

fun getOptionsList(questionItem: QuestionItem?): List<String> {
    val incorrectOptions = questionItem?.incorrect_answers
    val randomIndex = (0..incorrectOptions!!.size).random()

    val part1 = incorrectOptions.subList(0, randomIndex)
    val part2 = incorrectOptions.subList(randomIndex, incorrectOptions.size)

    val newList = mutableListOf<String>()
    newList.addAll(part1)
    if (questionItem != null) {
        newList.add(questionItem.correct_answer)
    }
    newList.addAll(part2)

    return newList.toList()
}

@Composable
fun PlaygroundLoaded(
    viewModel: QuestionsViewModel,
    navController: NavController
) {
    val questionsList = viewModel.data.value.data?.results?.toMutableList()
    var questionIndex = remember(questionsList) { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(AppColors.Purple)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigatingIcons(text = "EXIT") {
                viewModel.showDialog()
            }
            NumberOfQuestionsSolvedIndicator(
                modifier = Modifier.fillMaxWidth(0.6f),
                totalNumberOfQuestions = viewModel.quizTotalNumberOfQuestion.value,
                numberOfQuestionsCompleted = questionIndex.value+1
            )
            NavigatingIcons(text = "SKIP") {
                viewModel.skipped.value += 1
                if(questionIndex.value == (viewModel.quizTotalNumberOfQuestion.value - 1)) {
                    navController.navigate(QuizScreens.ResultScreen.name)
                } else {
                    questionIndex.value += 1
                }
            }
        }

        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(30.dp)),
            colors = CardDefaults.cardColors(Color.White),
        ) {
            DisplayQuestion(
                viewModel = viewModel,
                questionIndex = questionIndex.value,
                questionItem = questionsList?.get(questionIndex.value),
                totalNumberOfQuestions = viewModel.quizTotalNumberOfQuestion.value
            ) {
                if(questionIndex.value == (viewModel.quizTotalNumberOfQuestion.value - 1)) {
                    navController.navigate(QuizScreens.ResultScreen.name)
                } else {
                    questionIndex.value += 1
                }
            }
        }
    }
    if(viewModel.showAlertDialog.value) {
        AlertDialog(
            onDismissRequest = {
                viewModel.hideDialog()
            },
            onPositiveRequest = {
                viewModel.hideDialog()
            },
            onNegativeRequest = {
                viewModel.hideDialog()
                navController.navigate(
                    QuizScreens.CategoryScreen.name
                ) {
                    popUpTo(route = QuizScreens.CategoryScreen.name) {
                        inclusive = true
                    }
                }
                viewModel.resetQuiz()
            }
        )
    }
    BackHandler(
        enabled = true
    ) {
        viewModel.showDialog()
    }
}

@Composable
fun DisplayQuestion(
    viewModel: QuestionsViewModel,
    questionIndex: Int,
    questionItem: QuestionItem?,
    totalNumberOfQuestions: Int,
    onNextClicked: (Int) -> Unit
) {

    var selectedOptionIndex = remember(questionItem) { mutableStateOf<Int?>(null) }
    var updateSelectedIndex: (Int)->Unit = { selectedOptionIndex.value = it }
    var optionsList = remember(questionItem) { getOptionsList(questionItem) }

    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularTimerScreen(
            questionIndex = questionIndex
        ) {
            onNextClicked.invoke(questionIndex)
            viewModel.skipped.value += 1
        }
        Text(text = "QUESTION ${questionIndex+1} OF $totalNumberOfQuestions",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray)
        Text(text = Html.fromHtml(questionItem?.question).toString(),
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            optionsList.forEachIndexed { index, content ->
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .border(
                            width = 2.dp, brush = Brush.linearGradient(
                                colors = listOf(
                                    AppColors.VeryLightPurple,
                                    AppColors.VeryLightPurple
                                )
                            ),
                            shape = RoundedCornerShape(corner = CornerSize(15.dp))
                        )
                        .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
                        .background(
                            if (index == selectedOptionIndex.value) Color(0xFFE1DEF9)
                            else Color.White
                        )
                        .noRippleClickable {
                            updateSelectedIndex(index)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                        RadioButton(selected = radioButtonIndex.value == index,
//                            onClick = { updateAnswer(index) },
//                            colors = RadioButtonDefaults.colors(
//                                selectedColor =
//                                if(correctAnswerState.value == true) Color.Green
//                                else Color.Red
//                            ))
                    Text(text = Html.fromHtml(content).toString(),
                        modifier = Modifier
                            .padding(vertical = 15.dp, horizontal = 10.dp),
                        style = MaterialTheme.typography.titleMedium)
                }
            }
        }
//        Spacer(modifier = Modifier.weight(1f))
        NextButton(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp),
            text = if(questionIndex+1 == totalNumberOfQuestions) "SUBMIT" else "NEXT"
        ) {
            if(selectedOptionIndex.value != null) {
                if (optionsList[selectedOptionIndex.value!!] == questionItem?.correct_answer) {
                    viewModel.correctAnswers.value += 1
                } else {
                    viewModel.inCorrectAnswers.value += 1
                }
            } else {
                viewModel.skipped.value += 1
            }
            onNextClicked.invoke(questionIndex)
        }
    }
}