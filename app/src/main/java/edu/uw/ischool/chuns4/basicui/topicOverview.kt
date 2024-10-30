package edu.uw.ischool.chuns4.basicui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class topicOverview : AppCompatActivity() {
    lateinit var topicName : TextView
    lateinit var Description : TextView
    lateinit var numberQuestion : TextView
    lateinit var beginButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_topic_overview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val subjectName = intent.getStringExtra("name") ?: "Unknown Topic"
        val questionNumber = questionsList.questionsMap[subjectName]?.size ?: 0
        val topicDescription = getTopicDescription(subjectName)
        topicName = findViewById(R.id.topic_name_text)
        topicName.text = subjectName
        Description = findViewById(R.id.topic_description_text)
        Description.text = topicDescription
        numberQuestion = findViewById(R.id.topic_totalquestion)
        numberQuestion.text = "Total Number of Question: " + questionNumber
        beginButton = findViewById(R.id.begin_button)
        beginButton.setOnClickListener {
            val quizIntent = Intent(this, QuestionActivity::class.java)
            quizIntent.putExtra("topicName", subjectName) // Pass the topic name
            startActivity(quizIntent)
        }
    }

    private fun getTopicDescription(topic: String): String {
        return when (topic) {
            "Math" -> "Let's try some math question"
            "Physics" -> "Challenge yourself with physics questions."
            "Marvel Super Heroes" -> "How well do you know Marvel superheroes?"
            "History" -> "Explore questions on world history."
            "Biology" -> "Answer questions about biological sciences."
            else -> "Take a quiz on $topic!"
        }
    }
}