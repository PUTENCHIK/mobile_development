package com.example.fragmentweatherapp

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment

    var data: Data? = null
    var is_full: Boolean = false

    val icons = mapOf(
        "clear sky" to "01",
        "few clouds" to "02",
        "scattered clouds" to "03",
        "broken clouds" to "04",
        "shower rain" to "09",
        "rain" to "10",
        "thunderstorm" to "11",
        "snow" to "13",
        "mist" to "50",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fm = supportFragmentManager
        ft = fm.beginTransaction()
        fr2 = WeatherFull()

        val fr = fm.findFragmentById(R.id.frame_container)
        if (fr == null) {
            fr1 = WeatherShort()
            fm.beginTransaction().add(R.id.frame_container, fr1).commit()
        } else {
            fr1 = fr
        }

        val container = findViewById<FrameLayout>(R.id.frame_container)
        container.setOnClickListener {
            val ft = fm.beginTransaction()
            ft.replace(R.id.frame_container, when(is_full) {
                true -> fr1
                false -> fr2
            }).commit()
            is_full = !is_full
        }
    }

    fun showError(message: String) {
        val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    fun getNow(): String {
        val current: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
            "EEE, dd MMMM, HH:mm",
            Locale("ru", "RU"))
        return current.format(formatter)
    }
}