package com.example.quiz.source

import com.example.quiz.entity.NextQuestionRequest
import com.example.quiz.entity.QuestionRequest
import com.example.quiz.entity.QuestionResponse
import com.example.quiz.entity.ScoreResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface QuizService {

    //http://quiz.cppmania.com/question
    //this endpoint returns first question
    @POST("question")
    fun getQuestion(@Body request: QuestionRequest): Single<QuestionResponse>


    //http://quiz.cppmania.com/answer
    //this endpoint accepts answers and  returns question from second to fifth
    @POST("answer")
    fun getNextQuestion(@Body request: NextQuestionRequest): Single<QuestionResponse>

    //http://quiz.cppmania.com/answer
    //this endpoint accepts answers and also get final score after question 5
    @POST("answer")
    fun getFinalScore(@Body request: NextQuestionRequest): Single<ScoreResponse>
}