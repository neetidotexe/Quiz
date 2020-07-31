package com.example.quiz.source

import com.example.quiz.ui.UserId
import com.example.quiz.entity.NextQuestionRequest
import com.example.quiz.entity.QuestionRequest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class QuizNetworkDataSourceTest {
    lateinit var apiService: QuizService
    lateinit var mockWebService: MockWebServer

    @Before
    fun setUp() {
        mockWebService = MockWebServer()
        mockWebService.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebService.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(QuizService::class.java)
    }

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

    @Test
    fun `getFirstQuestionWhenClickedOnStartButton`() {
        mockWebService.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(
                getJson("success_response.json")
            )
        )

        val response = apiService.getQuestion(QuestionRequest(UserId.userId)).blockingGet()
        assertEquals(response.question?.questionText, "Which out of these are fruits?")
    }

    @Test
    fun `getSecondQuestionAndPassAnswerOfFirstQuestionDetails`() {
        mockWebService.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(
                getJson("pass_answer_and_get_second_question.json")
            )
        )

        val answers = mutableListOf<Int>(2,3)

        val response = apiService.getNextQuestion(NextQuestionRequest(UserId.userId, currentAnswers = answers)).blockingGet()
        assertEquals(response.question?.questionText, "Which out of these fall into the category of mammals?")
    }

    private fun getJson(path: String): String {
        val uri = this::class.java.classLoader!!.getResource(path)
        val file = File(uri!!.path)
        return String(file.readBytes())
    }
}