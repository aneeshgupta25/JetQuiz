package com.example.jetquiz.repository

import android.util.Log
import com.example.jetquiz.data.DataOrException
import com.example.jetquiz.model.Question
import com.example.jetquiz.model.QuestionItem
import com.example.jetquiz.network.QuestionsAPI
import javax.inject.Inject

class QuestionsRepository @Inject constructor(private val api: QuestionsAPI) {

    private val dataOrException =
        DataOrException<Question,
            Boolean,
            Exception>()

    suspend fun getAllQuestions(): DataOrException<Question, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions(
                11,
                11,
                "easy",
                "multiple"
            )
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch(exception: Exception) {
            dataOrException.e = exception
            Log.d("Questions Exception", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }

}