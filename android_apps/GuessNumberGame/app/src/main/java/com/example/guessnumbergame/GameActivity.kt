package com.example.guessnumbergame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {
    var left: Int = 0
    var right: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        left = intent.getIntExtra("left", 0)
        right = intent.getIntExtra("right", 100)

//        Log.d("set_limits", "($left, $right)")
        updateQuestion()
    }

    fun getMid(): Int = (right + left) / 2

    fun updateQuestion() {
        if (left == right) {
            return showAnswer(left)
        }
        val tv_question = findViewById<TextView>(R.id.question)
        tv_question.text = when {
            (right - left == 1) -> "($left, $right)\nЧисло равно ${left}?"
            else -> "($left, $right)\nЧисло больше ${getMid()}?"
        }
    }

    fun showAnswer(answer: Int) {
        val tv_question = findViewById<TextView>(R.id.question)
        tv_question.text = "Это $answer"
        val ll_buttons = findViewById<LinearLayout>(R.id.answerContainer)
        ll_buttons.visibility = View.INVISIBLE
    }

    fun onYesNoClick(view: View) {
        when (view.id) {
            R.id.yes -> {
                if (right - left == 1) {
                    showAnswer(left)
                } else {
                    left = getMid()+1
                    updateQuestion()
                }
            }
            R.id.no -> {
                if (right - left == 1) {
                    showAnswer(right)
                } else {
                    right = getMid()
                    updateQuestion()
                }
            }
        }
    }

    fun backToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}