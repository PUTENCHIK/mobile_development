package com.example.guessnumbergame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    fun onGuessClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        val et_left = findViewById<EditText>(R.id.left)
        val et_right = findViewById<EditText>(R.id.right)
        val tv_error = findViewById<TextView>(R.id.error)
        val value: Int

        if (et_left.text.length > 0) {
            value = et_left.text.toString().toInt()
            intent.putExtra("left", value)
        } else {
            // Make error block visible
//            tv_error.visibility
        }
        intent.putExtra("right", 999)
        startActivity(intent)
    }
}