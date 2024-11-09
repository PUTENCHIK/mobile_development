package com.example.randomfilmapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class IntContainer(var value: Int)


class MainActivity : AppCompatActivity() {
    lateinit var films: Array<String>
    lateinit var currentLengthFilms: IntContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        films = resources.getStringArray(R.array.films)
        currentLengthFilms = IntContainer(films.size)
        setRandomFilm()
    }


    private fun setRandomFilm() {
        val tvFilm = findViewById<TextView>(R.id.film_name)
        println(currentLengthFilms.value)
        when (currentLengthFilms.value) {
            0 -> {
                val text = resources.getString(R.string.array_ended)
                tvFilm.text = text
            }
            1 -> {
                tvFilm.text = films[0]
                currentLengthFilms.value -= 1
            }
            else -> {
                val index = Random.nextInt(0, currentLengthFilms.value - 1)
                tvFilm.text = films[index]

                val buffer = films[currentLengthFilms.value - 1]
                films[currentLengthFilms.value - 1] = films[index]
                films[index] = buffer

                currentLengthFilms.value -= 1
            }
        }
    }

    fun reset(view: View) {
        currentLengthFilms.value = films.size
        setRandomFilm()
    }

    fun next(view: View) {
        setRandomFilm()
    }
}