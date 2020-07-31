package com.example.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quiz.R

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val toast = Toast.makeText(this, "Can not press back button until quiz finishes", Toast.LENGTH_SHORT)
        toast.show()
    }
}


object UserId {
    var userId: String = (System.currentTimeMillis() / 1000L).toString()
}
