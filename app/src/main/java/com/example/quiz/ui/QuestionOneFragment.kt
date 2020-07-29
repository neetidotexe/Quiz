package com.example.quiz.ui

import android.os.Bundle
import android.provider.Contacts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.UserId

class QuestionOneFragment : Fragment() {

    lateinit var questionText : TextView
    lateinit var optionOne : CheckBox
    lateinit var optionTwo : CheckBox
    lateinit var optionThree : CheckBox
    lateinit var optionFour : CheckBox
    lateinit var nextQuestion : ImageView
    lateinit var viewModel: QuizViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root= inflater.inflate(R.layout.fragment_question_one, container, false)

        questionText = root.findViewById(R.id.question_one)
        optionOne = root.findViewById(R.id.answer_one_check_box_one)
        optionTwo = root.findViewById(R.id.answer_one_check_box_two)
        optionThree = root.findViewById(R.id.answer_one_check_box_three)
        optionFour = root.findViewById(R.id.answer_one_check_box_four)
        nextQuestion = root.findViewById(R.id.question_one_screen_next_image)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        viewModel.fetchQuestionDetails(UserId.userId)

        viewModel.question.observe(viewLifecycleOwner, Observer {
            questionText.text = it.question?.questionText
            optionOne.text = it.question?.options?.get(0)?.optionText
            optionTwo.text = it.question?.options?.get(1)?.optionText
            optionThree.text = it.question?.options?.get(2)?.optionText
            optionFour.text = it.question?.options?.get(3)?.optionText
        })


        return root
    }


}
