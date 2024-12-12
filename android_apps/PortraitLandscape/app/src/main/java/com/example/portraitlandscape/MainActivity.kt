package com.example.portraitlandscape

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val images = listOf(R.drawable.pudge, R.drawable.baratrum, R.drawable.sf)
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        index = getSharedPref().getInt("index", 0)
        updateImage()
    }

    fun getSharedPref(): SharedPreferences {
        return this.getSharedPreferences(
            "portrait_landscape",
            Activity.MODE_PRIVATE
        )
    }

    fun updateImage() {
        val ivImage = findViewById<ImageView>(R.id.image)
        ivImage.setImageResource(images[index])
        val tvCounter = findViewById<TextView>(R.id.counter)
        tvCounter.text = resources.getString(R.string.counter_text, index+1, images.size)

        with (getSharedPref().edit()) {
            putInt("index", index)
            apply()
        }
    }

    fun previousImage(view: View) {
        index = if (index > 0) index-1 else images.size-1
        updateImage()
    }

    fun nextImage(view: View) {
        index = if (index+1 < images.size) index+1 else 0
        updateImage()
    }
}