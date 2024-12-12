package com.example.portraitlandscape

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val images = listOf(R.drawable.pudge, R.drawable.baratrum, R.drawable.sf)
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun updateImage() {
        val ivImage = findViewById<ImageView>(R.id.image)
        ivImage.setImageResource(images[index])
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