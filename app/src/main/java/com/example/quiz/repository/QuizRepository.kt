package com.example.quiz.repository

import com.example.quiz.entity.QuestionResponse
import com.example.quiz.entity.ScoreResponse
import com.example.quiz.source.QuizClient
import com.example.quiz.source.QuizNetworkDataSource
import io.reactivex.Single

class QuizRepository {
    private val apiService = QuizClient.getClient()
    private val quizNetworkDataSource = QuizNetworkDataSource(apiService)

    fun fetchQuestionDetails(userId : String ): Single<QuestionResponse> {
        return quizNetworkDataSource.getQuestionDetails(userId)
    }

    fun fetchNextQuestionDetails(userId: String , answers : MutableList<Int>) : Single<QuestionResponse>{
        return quizNetworkDataSource.getNextQuestionDetails(userId , answers)
    }

    fun fetchFinalScoreDetails(userId: String , answers : MutableList<Int>) : Single<ScoreResponse>{
        return quizNetworkDataSource.getFinalScoreDetails(userId , answers)
    }

}