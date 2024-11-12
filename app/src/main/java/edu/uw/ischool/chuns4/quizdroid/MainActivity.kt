package edu.uw.ischool.chuns4.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var quizRepo: TopicRepositoryInterface
    private lateinit var mainText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val quizApp = application as QuizApp
        quizRepo = quizApp.topicRepository
        val topics = quizRepo.getAllTopic()
        val listView: ListView = findViewById(R.id.topic_list)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,  topics)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val selected = topics[position]
            val next = Intent(this, topicOverview::class.java)
         next.putExtra("name", selected)
         startActivity(next)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_preferences -> {
                startActivity(Intent(this, Preferences::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}