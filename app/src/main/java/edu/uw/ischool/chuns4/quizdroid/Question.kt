package edu.uw.ischool.chuns4.quizdroid

import java.io.Serializable

data class Question(
    var questionText: String,
    val answers: List<String>,
    val correctQuestionIndex: Int
) : Serializable