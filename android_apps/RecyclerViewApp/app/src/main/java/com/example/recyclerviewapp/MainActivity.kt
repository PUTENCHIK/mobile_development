package com.example.recyclerviewapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    val colors = generateColors()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rec_view)
        val adapter = MyAdapter(LayoutInflater.from(this))
        adapter.submitList(colors)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    fun encodeColor(R: Int, G: Int, B: Int): Int {
        return Color.rgb(R, G, B)
    }

    fun decodeColor(color: Int): List<Int> {
        val code = listOf(
            (color shr 16) and 0xff,
            (color shr 8) and 0xff,
            (color) and 0xff,
        )
        return code
    }

    fun generateColors(): MutableList<Int> {
        val n = 2
        val lower = Color.RED
        val upper = Color.BLUE
        val array = mutableListOf<Int>()

        val lower_dec = decodeColor(lower)
        val upper_dec = decodeColor(upper)

        for (i in 0..n) {
            val t = i.toDouble() / n
            array.add(encodeColor(
                (lower_dec[0] * (1 - t) + upper_dec[0] * t).toInt(),
                (lower_dec[1] * (1 - t) + upper_dec[1] * t).toInt(),
                (lower_dec[2] * (1 - t) + upper_dec[2] * t).toInt(),
            ))
        }

        return array
    }
}