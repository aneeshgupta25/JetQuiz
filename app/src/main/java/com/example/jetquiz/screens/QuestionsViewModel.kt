package com.example.jetquiz.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetquiz.data.CategoryList
import com.example.jetquiz.data.DataOrException
import com.example.jetquiz.model.Category
import com.example.jetquiz.model.Question

import com.example.jetquiz.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionsRepository): ViewModel(){

    var data: MutableState<DataOrException<Question, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )
    var listOfQuizCategory = CategoryList.getCategories()

    var quizCategory: MutableState<Int?> = mutableStateOf(null)
    var quizCategoryId: MutableState<Int?> = mutableStateOf(null)
    var quizLevel: MutableState<Int?> = mutableStateOf(0)
    var quizLevelString: MutableState<String?> = mutableStateOf(null)
    var quizTotalNumberOfQuestion: MutableState<Int> = mutableStateOf(10)
    var correctAnswers: MutableState<Int> = mutableStateOf(0)
    var inCorrectAnswers: MutableState<Int> = mutableStateOf(0)
    var skipped: MutableState<Int> = mutableStateOf(0)

    init {
//        getAllQuestions()
    }

    fun getAllQuestions() {
        viewModelScope.launch {
            Log.d("TAG", "getAllQuestions: ${data}")
            data.value.loading = true
            data.value = repository.getAllQuestions(
                amount = quizTotalNumberOfQuestion.value,
                category = quizCategoryId.value,
                level = quizLevelString.value!!
            )
            Log.d("TAG", "getAllQuestions: Message Received!")
            if(data.value.data?.results.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }

    fun getQuizCategories(): List<Category> = listOfQuizCategory
    fun updateQuizCategory(category: Int?) {
        quizCategory.value = category
        quizCategoryId.value = listOfQuizCategory[category!!].id
    }
    fun updateQuizLevel(level: Int?) {
        quizLevel.value = level
        when(level) {
            1 -> quizLevelString.value = "easy"
            2 -> quizLevelString.value = "medium"
            3 -> quizLevelString.value = "hard"
            else -> null
        }
    }

    fun getQuizTheme(): String {
        return listOfQuizCategory[quizCategory.value!!].category
    }

    fun getQuizLevel(): String? {
        if(quizLevel.value == 1) return "Newbie"
        else if(quizLevel.value == 2) return "Continuing"
        else if(quizLevel.value == 3) return "Experienced"
        else return null
    }

    fun addQuestion() {
        quizTotalNumberOfQuestion.value += 1
    }

    fun deleteQuestion() {
        if(quizTotalNumberOfQuestion.value > 1)
            quizTotalNumberOfQuestion.value -= 1
    }

    fun resetQuiz() {
        data.value = DataOrException(null, true, Exception(""))
        quizCategory.value = null
        quizCategoryId.value = null
        quizLevel.value = 0
        quizLevelString.value = null
        quizTotalNumberOfQuestion.value = 10
        correctAnswers.value = 0
        inCorrectAnswers.value = 0
        skipped.value = 0
    }

}