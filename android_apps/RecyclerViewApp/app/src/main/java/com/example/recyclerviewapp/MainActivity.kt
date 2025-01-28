package com.example.recyclerviewapp

import android.graphics.Color
import android.os.Bundle
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

    fun generateColors(): MutableList<Int> {
        val n = 5
        val lower = Color.RED
        val upper = Color.BLUE
        val array = mutableListOf<Int>()

        for (i in 1..n) {
            array.add(lower + i/n * (upper - lower))
        }
        return array
    }
}