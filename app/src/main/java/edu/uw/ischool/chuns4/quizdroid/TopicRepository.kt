package edu.uw.ischool.chuns4.quizdroid

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.io.InputStream
import kotlin.math.log


interface TopicRepositoryInterface {
    fun getTopics(): List<Topic>
    fun getQuestionsForTopic(target: String?): List<Question>
    fun getAllTopic(): List<String>
    fun getShortDescriptionForTarget(target: String?): String
    fun getLongDescriptionForTarget(target: String?): String
}

class TopicRepository (private val context: Context): TopicRepositoryInterface {
    private var topic: List<Topic> = listOf()

    private fun loadTopics(): List<Topic> {
        val topics = mutableListOf<Topic>()
        try {
            val inputStream: InputStream = context.assets.open("chuns4_custom_questions.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val topicJson = jsonArray.getJSONObject(i)
                val title = topicJson.getString("title")
                val shortDescription = topicJson.getString("shortDescription")
                val longDescription = topicJson.getString("longDescription")
                val questionsList = mutableListOf<Question>()
                val questionsArray = topicJson.getJSONArray("questionsList")
                for (j in 0 until questionsArray.length()) {
                    val questionJson = questionsArray.getJSONObject(j)
                    val questionText = questionJson.getString("questionText")
                    val optionsArray = questionJson.getJSONArray("answers")
                    val optionsList = mutableListOf<String>()
                    for (k in 0 until optionsArray.length()) {
                        optionsList.add(optionsArray.getString(k))
                    }
                    val correctAnswerIndex = questionJson.getInt("correctQuestionIndex")
                    questionsList.add(Question(questionText, optionsList, correctAnswerIndex))
                }

                topics.add(Topic(title, shortDescription, longDescription, questionsList))
            }

        } catch (e: Exception) {
            println("error in getting Json File ${e.message}")
        }
        return topics
    }

    init {
        topic = loadTopics()
    }


    override fun getTopics(): List<Topic> {
        return topic
    }

    override fun getAllTopic(): List<String> {
        return topic.map { it.title }
    }

    override fun getShortDescriptionForTarget(target: String?): String {
        return topic.find { it.title == target }?.shortDescription ?: throw IllegalArgumentException("Topic does not exist.")
    }

    override fun getLongDescriptionForTarget(target: String?): String {
        return topic.find { it.title == target }?.longDescription ?: throw IllegalArgumentException("Topic does not exist.")
    }

    override fun getQuestionsForTopic(target: String?): List<Question> {
        return topic.find { it.title == target }?.questionsList ?: throw IllegalArgumentException("Topic does not exist.")
    }

}