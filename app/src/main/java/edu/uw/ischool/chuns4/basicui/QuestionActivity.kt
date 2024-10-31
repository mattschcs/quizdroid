package edu.uw.ischool.chuns4.basicui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {
    lateinit var questionTextView: TextView
    lateinit var answerRadioGroup: RadioGroup
    lateinit var submitButton: Button
    var questions: ArrayList<Question>? = null
    var subjectName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionTextView = findViewById(R.id.questionTextView)
        answerRadioGroup = findViewById(R.id.answerRadioGroup)
        submitButton = findViewById(R.id.submitButton)
        subjectName = intent.getStringExtra("topicName") ?: "Unknown Topic"
        var currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)
        var correctAnswers = intent.getIntExtra("correctAnswers", 0)
        questions = ArrayList(questionsList.questionsMap[subjectName] ?: emptyList())
        questionTextView.text = questions!!.get(currentQuestionIndex).text
        if (questions != null && !questions!!.isEmpty()) {

            displayQuestion(questions!!, currentQuestionIndex)
            answerRadioGroup.setOnCheckedChangeListener { _, _ ->
                submitButton.isEnabled = answerRadioGroup.checkedRadioButtonId != -1
            }

            submitButton.setOnClickListener {
                val selectedAnswerIndex = answerRadioGroup.indexOfChild(findViewById(answerRadioGroup.checkedRadioButtonId))
                navigateToAnswerPage(selectedAnswerIndex, currentQuestionIndex,correctAnswers)
            }
        }

    }

    private fun displayQuestion(targetQuestion: ArrayList<Question>,currentQuestionIndex: Int) {
       val currentQuestion = targetQuestion.get(currentQuestionIndex)
        currentQuestion.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(this)
            radioButton.text = option
            answerRadioGroup.addView(radioButton)
        }
        submitButton.isEnabled = false
    }

    private fun navigateToAnswerPage(selectedAnswerIndex: Int, currentQuestionIndex: Int, correctAnswers: Int) {
        val correctAnswerIndex = questions!![currentQuestionIndex].correctAnswerIndex
        val next = Intent(this, AnswerActivity::class.java)
            next.putExtra("userAnswerIndex", selectedAnswerIndex)
            next.putExtra("correctAnswerIndex", correctAnswerIndex)
            next.putExtra("totalQuestions", questions!!.size)
            next.putExtra("currentQuestionIndex", currentQuestionIndex)
            next.putExtra("subjectName", subjectName)
            next.putExtra("correctAnswers", correctAnswers)
        startActivity(next)
    }

}