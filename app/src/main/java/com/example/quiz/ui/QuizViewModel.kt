package com.example.quiz.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.entity.QuestionResponse
import com.example.quiz.repository.QuizRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class QuizViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val quizRepository = QuizRepository()

    private var _question = MutableLiveData<QuestionResponse>()
    val question: LiveData<QuestionResponse>
        get() = _question

    @SuppressLint("CheckResult")
    fun fetchQuestionDetails(userId : String){
        compositeDisposable.add(
            quizRepository.fetchQuestionDetails(userId)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _question.postValue(it)
                    },
                    {

                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}