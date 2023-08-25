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

    var quizCategory: MutableState<Int?> = mutableStateOf(null)


    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            Log.d("TAG", "getAllQuestions: ${data}")
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if(data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }

    fun getQuizCategories(): List<Category> = CategoryList.getCategories()
    fun updateQuizCategory(category: Int?) { quizCategory.value = category }

}