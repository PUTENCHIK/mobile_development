package com.example.guessnumbergame

import android.os.Bundle
import android.util.Log
import android.view.View
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
        right = intent.getIntExtra("right", 0)

        Log.d("mytag", "($left, $right)")
    }

    fun onYesNoClick(view: View) {
        when (view.id) {
            R.id.yes -> left = 0
            R.id.no -> right = 0
        }
    }
}