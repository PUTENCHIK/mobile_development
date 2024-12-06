package com.example.colortiles

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var tiles: TilesView
    private var n: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(TilesView(this))
    }

    fun restartOnClick(view: View) {
        val container = findViewById<LinearLayout>(R.id.field_container)
        container.removeAllViews()

        tiles = TilesView(this, n)
        tiles.layoutParams = ViewGroup.LayoutParams(container.width, container.width)

        updateClicks()

        container.addView(tiles)
    }

    fun setSize(view: View) {
        val edSize = findViewById<EditText>(R.id.size_input)
        val value: Int
        try {
            value = edSize.text.toString().toInt()
        } catch (ex: Exception) {
            return
        }
        n = when {
            value < 1 -> 1
            value > 10 -> 10
            else -> value
        }

        edSize.setText("")
        edSize.hint = n.toString()
        edSize.clearFocus()

        restartOnClick(view)
    }

    fun fieldOnClick(view: View) {
        updateClicks()
    }

    fun updateClicks() {
        val tvClicksAmount = findViewById<TextView>(R.id.tv_clicks)
        tvClicksAmount.visibility = View.VISIBLE
        tvClicksAmount.text = resources.getString(R.string.tv_clicks) + " " + tiles.clicks.toString()
    }
}