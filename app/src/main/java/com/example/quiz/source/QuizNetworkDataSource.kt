package com.example.quiz.source

import com.example.quiz.entity.NextQuestionRequest
import com.example.quiz.entity.QuestionRequest
import com.example.quiz.entity.QuestionResponse
import io.reactivex.Single

class QuizNetworkDataSource(private val apiService: QuizService) {

    fun getQuestionDetails(userId : String) : Single<QuestionResponse> {

        return apiService.getQuestion(QuestionRequest(userId = userId))

    }

    fun getNextQuestionDetails(userId: String , answers : MutableList<Int>): Single<QuestionResponse> {

        return apiService.getNextQuestion(NextQuestionRequest(userId = userId , currentAnswers = answers))
    }
}