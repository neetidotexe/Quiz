package com.example.quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

import com.example.quiz.R

class HomeScreenFragment : Fragment() {

    lateinit var startImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home_screen, container, false)

        startImage = root.findViewById(R.id.home_screen_start_image)

        //click event for Start - navigate to question one
        startImage.setOnClickListener {
            showQuestionOne()
        }

        return root
    }

    fun showQuestionOne() {
        findNavController().navigate(R.id.action_homeScreenFragment_to_questionOneFragment)
    }
}
