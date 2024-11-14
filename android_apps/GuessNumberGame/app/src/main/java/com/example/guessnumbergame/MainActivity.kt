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

    fun showError(text: String) {
        val tv_error = findViewById<TextView>(R.id.error)
        tv_error.visibility = View.VISIBLE
        tv_error.text = text
    }

    fun onGuessClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        val et_left = findViewById<EditText>(R.id.left)
        val et_right = findViewById<EditText>(R.id.right)

        val value: Int

        if (et_left.text.length > 0) {
            value = et_left.text.toString().toInt()
            intent.putExtra("left", value)
        } else {
            return showError(getString(R.string.error_no_left))
        }

        if (et_right.text.length > 0) {
            if (value >= et_right.text.toString().toInt()) {
                return showError(getString(R.string.error_bad_values))
            }
            intent.putExtra("right", et_right.text.toString().toInt())
        } else {
            return showError(getString(R.string.error_no_right))
        }

        startActivity(intent)
    }
}