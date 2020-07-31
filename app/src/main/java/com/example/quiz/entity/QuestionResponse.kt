package com.example.quiz.entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class QuestionResponse(

    @SerializedName("question")
    val question: Question?
)

data class Question(

    @PrimaryKey
    @SerializedName("id")
    val questionNumber : String,

    @SerializedName("text")
    val questionText : String,

    @SerializedName("answers")
    val options : List<AnswerResponse>
)

data class AnswerResponse(

    @SerializedName("id")
    val optionId : Int,

    @SerializedName("text")
    val optionText : String
)

data class ScoreResponse(

    @SerializedName("score")
    val finalScore : Int
)

