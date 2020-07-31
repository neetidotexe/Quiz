package com.example.quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.quiz.R
import kotlin.system.exitProcess

class FinalScoreFragment : Fragment() {

    lateinit var finalScoreText : TextView
    lateinit var correctAnswerText : TextView
    lateinit var incorrectAnswerText : TextView
    lateinit var closeApp : ImageView
    lateinit var viewModel: QuizViewModel

    var passSelectedAnswersOfPreviousQuestion = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_final_score, container, false)

        finalScoreText = root.findViewById(R.id.final_score_value_text)
        correctAnswerText = root.findViewById(R.id.correct_answer_text)
        incorrectAnswerText = root.findViewById(R.id.incorrect_answer_text)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        //function to get answers from previous question and add it to a mutable list of Int
        getAnswersOfPreviousQuestion()

        viewModel.fetchFinalScoreDetails(UserId.userId, passSelectedAnswersOfPreviousQuestion)

        viewModel.score.observe(viewLifecycleOwner, Observer {
            finalScoreText.text = ((it.finalScore * 100)/5).toString()
            correctAnswerText.text = it.finalScore.toString()
            incorrectAnswerText.text = (5.minus(it.finalScore)).toString()
        })

        closeApp.setOnClickListener {

            exitProcess(0)
        }

        return root
    }

    private fun getAnswersOfPreviousQuestion() {
        //fetch the values of previous question's answer
        val optionOneSelected = arguments?.getBoolean("optionOneSelected")!!
        val optionTwoSelected = arguments?.getBoolean("optionTwoSelected")!!
        val optionThreeSelected = arguments?.getBoolean("optionThreeSelected")!!
        val optionFourSelected = arguments?.getBoolean("optionFourSelected")!!


        //add these values in a utable list
        if (optionOneSelected) {
            passSelectedAnswersOfPreviousQuestion.add(0)
        }
        if (optionTwoSelected) {
            passSelectedAnswersOfPreviousQuestion.add(1)
        }
        if (optionThreeSelected) {
            passSelectedAnswersOfPreviousQuestion.add(2)
        }
        if (optionFourSelected) {
            passSelectedAnswersOfPreviousQuestion.add(3)
        }
    }
}
