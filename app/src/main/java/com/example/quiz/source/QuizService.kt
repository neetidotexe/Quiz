package com.example.quiz.source

import com.example.quiz.entity.QuestionRequest
import com.example.quiz.entity.QuestionResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface QuizService {

    @POST("question")
    fun getQuestion(@Body request: QuestionRequest): Single<QuestionResponse>

}