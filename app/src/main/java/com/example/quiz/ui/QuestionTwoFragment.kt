package com.example.quiz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quiz.R

class QuestionTwoFragment : Fragment() {

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

    var passSelectedAnswersOfPreviousQuestion = mutableListOf<Int>()
    private val selectedAnswersList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_question_two, container, false)

        questionText = root.findViewById(R.id.question_two)
        optionOne = root.findViewById(R.id.answer_two_check_box_one)
        optionTwo = root.findViewById(R.id.answer_two_check_box_two)
        optionThree = root.findViewById(R.id.answer_two_check_box_three)
        optionFour = root.findViewById(R.id.answer_two_check_box_four)
        nextQuestion = root.findViewById(R.id.question_two_screen_next_image)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        //function to get answers from previous question and add it to a mutable list of Int
        getAnswersOfPreviousQuestion()


        viewModel.fetchNextQuestionDetails(UserId.userId, passSelectedAnswersOfPreviousQuestion)

        viewModel.question.observe(viewLifecycleOwner, Observer {
            questionText.text = it.question?.questionText
            optionOne.text = it.question?.options?.get(0)?.optionText
            optionTwo.text = it.question?.options?.get(1)?.optionText
            optionThree.text = it.question?.options?.get(2)?.optionText
            optionFour.text = it.question?.options?.get(3)?.optionText
            optionOneId = it.question?.options?.get(0)?.optionId
            optionTwoId = it.question?.options?.get(1)?.optionId
            optionThreeId = it.question?.options?.get(2)?.optionId
            optionFourId = it.question?.options?.get(3)?.optionId
        })

        //click listener for Next
        nextQuestion.setOnClickListener {
            showQuestionThree()
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

    private fun showQuestionThree() {

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
            val toast =
                Toast.makeText(context, "Please select atleast one answer", Toast.LENGTH_SHORT)
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
                QuestionTwoFragmentDirections.actionQuestionTwoFragmentToQuestionThreeFragment(
                    optionOneSelected,
                    optionTwoSelected,
                    optionThreeSelected,
                    optionFourSelected
                )
            findNavController().navigate(directions)
        }
    }
}
