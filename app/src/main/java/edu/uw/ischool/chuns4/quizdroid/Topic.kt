package edu.uw.ischool.chuns4.quizdroid

data class Topic (
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questionsList: List<Question>
)