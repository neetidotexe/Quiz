package com.example.quiz.entity

import com.google.gson.annotations.SerializedName

data class QuestionRequest(

    @SerializedName("user_id")
    val userId: String
)
