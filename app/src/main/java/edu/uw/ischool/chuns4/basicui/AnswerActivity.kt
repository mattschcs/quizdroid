package edu.uw.ischool.chuns4.basicui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AnswerActivity : AppCompatActivity() {
    lateinit var resultTextView: TextView
    lateinit var nextButton: Button
    lateinit var finishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_answer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultTextView = findViewById(R.id.resultTextView)
        nextButton = findViewById(R.id.nextButton)
        finishButton = findViewById(R.id.finishButton)

        val userAnswerIndex = intent.getIntExtra("userAnswerIndex", -1)
        var correctAnswerIndex = intent.getIntExtra("correctAnswerIndex", -1)
        var totalQuestions = intent.getIntExtra("totalQuestions", 0)
        var correctAnswers = intent.getIntExtra("correctAnswers", 0)
        var currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)
        val subjectName = intent.getStringExtra("subjectName")
        if (userAnswerIndex == correctAnswerIndex) {
            correctAnswers++
            resultTextView.text = "Correct! The answer is: ${questionsList.questionsMap[subjectName]!![currentQuestionIndex].options[correctAnswerIndex]}"
        } else {
            resultTextView.text = "Incorrect. The correct answer is: ${questionsList.questionsMap[subjectName]!![currentQuestionIndex].options[correctAnswerIndex]}"
        }

        resultTextView.append("\nYou have $correctAnswers out of ${currentQuestionIndex + 1} correct.")
        if (currentQuestionIndex < totalQuestions - 1) {
            nextButton.setOnClickListener {
                val next = Intent(this, QuestionActivity::class.java)
                next.putExtra("topicName", subjectName)
                next.putExtra("currentQuestionIndex", currentQuestionIndex + 1)
                next.putExtra("correctAnswers",correctAnswers)
                startActivity(next)
            }
        } else {
            nextButton.visibility = View.GONE
            finishButton.setOnClickListener {
                val intent = Intent(this, topicOverview::class.java)
                startActivity(intent)
            }
        }
    }
}