package com.example.sumtwonumbersapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calculate(view: View) {
        val etNumA = findViewById<EditText>(R.id.numA)
        val etNumB = findViewById<EditText>(R.id.numB)
        val sum = etNumA.text.toString().toInt() + etNumB.text.toString().toInt()

        val tvSum = findViewById<TextView>(R.id.sum)
        tvSum.text = sum.toString()
    }
}