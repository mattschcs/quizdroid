package edu.uw.ischool.chuns4.quizdroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.Button
import androidx.preference.PreferenceManager

class Preferences : AppCompatActivity() {
    private lateinit var urlEditText: EditText
    private lateinit var intervalEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferences)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        urlEditText = findViewById(R.id.url_edit_text)
        intervalEditText = findViewById(R.id.interval_edit_text)
        saveButton = findViewById(R.id.save_button)
        urlEditText.setText(sharedPreferences.getString("question_url", ""))
        intervalEditText.setText(sharedPreferences.getInt("check_interval", 15).toString())

        saveButton.setOnClickListener {
            savePreferences()
        }
    }


    private fun savePreferences() {
        val url = urlEditText.text.toString()
        val interval = intervalEditText.text.toString().toIntOrNull() ?: 0

        sharedPreferences.edit()
            .putString("question_url", url)
            .putInt("check_interval", interval)
            .apply()
        finish()


    }
}