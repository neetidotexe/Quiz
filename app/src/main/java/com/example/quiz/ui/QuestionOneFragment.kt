package com.example.quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quiz.R

class QuestionOneFragment : Fragment(){

    lateinit var questionText: TextView
    lateinit var optionOne: CheckBox
    lateinit var optionTwo: CheckBox
    lateinit var optionThree: CheckBox
    lateinit var optionFour: CheckBox
    lateinit var nextQuestion: ImageView

    lateinit var viewModel: QuizViewModel

    var optionOneId: Int? = null
    var optionTwoId: Int? = null
    var optionThreeId: Int? = null
    var optionFourId: Int? = null

    private val selectedAnswersList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_question_one, container, false)

        questionText = root.findViewById(R.id.question_one)
        optionOne = root.findViewById(R.id.answer_one_check_box_one)
        optionTwo = root.findViewById(R.id.answer_one_check_box_two)
        optionThree = root.findViewById(R.id.answer_one_check_box_three)
        optionFour = root.findViewById(R.id.answer_one_check_box_four)
        nextQuestion = root.findViewById(R.id.question_one_screen_next_image)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        //call function  from view model to load first question
        viewModel.fetchQuestionDetails(UserId.userId)

        //populate first question and its options
        viewModel.question.observe(viewLifecycleOwner, Observer {
            questionText.text = it.question?.questionText
            optionOne.text = it.question?.options?.get(0)?.optionText
            optionTwo.text = it.question?.options?.get(1)?.optionText
            optionThree.text = it.question?.options?.get(2)?.optionText
            optionFour.text = it.question?.options?.get(3)?.optionText

            //this is to hold ids of answer options
            optionOneId = it.question?.options?.get(0)?.optionId
            optionTwoId = it.question?.options?.get(1)?.optionId
            optionThreeId = it.question?.options?.get(2)?.optionId
            optionFourId = it.question?.options?.get(3)?.optionId
        })

        //click listener for Next
        nextQuestion.setOnClickListener {
            showQuestionTwo()
        }

        return root
    }

    private fun showQuestionTwo() {

        /* based on the answer options checked, add the answer option ids (getting from API)
        to the mutable list of selectedAnswersList */
        if (optionOne.isChecked)
            optionOneId?.let { selectedAnswersList.add(it) }

        if (optionTwo.isChecked)
            optionTwoId.let {
                if (it != null) {
                    selectedAnswersList.add(it)
                }
            }

        if (optionThree.isChecked)
            optionThreeId?.let { selectedAnswersList.add(it) }

        if (optionFour.isChecked)
            optionFourId?.let { selectedAnswersList.add(it) }


        if (selectedAnswersList.size == 0) {
            val toast = Toast.makeText(context, "Please select atleast one answer", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            /*
             based on the values in selectedAnswersList, add the boolean value true to the option
             which are used as arguments to be passed to next fragment
              */
            var optionOneSelected = false
            var optionTwoSelected = false
            var optionThreeSelected = false
            var optionFourSelected = false
            for (i in 0..selectedAnswersList.size - 1) {
                when (selectedAnswersList.get(i)) {
                    0 -> optionOneSelected = true
                    1 -> optionTwoSelected = true
                    2 -> optionThreeSelected = true
                    3 -> optionFourSelected = true
                }
            }

            val directions =
                QuestionOneFragmentDirections.actionQuestionOneFragmentToQuestionTwoFragment(
                    optionOneSelected,
                    optionTwoSelected,
                    optionThreeSelected,
                    optionFourSelected
                )
            findNavController().navigate(directions)

        }
    }
}
