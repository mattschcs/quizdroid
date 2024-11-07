package edu.uw.ischool.chuns4.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    val TAG = QuizApp::class.java.name
    lateinit var topicRepository: TopicRepositoryInterface

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Quiz App is on create")
        topicRepository = TopicRepository()
    }
}