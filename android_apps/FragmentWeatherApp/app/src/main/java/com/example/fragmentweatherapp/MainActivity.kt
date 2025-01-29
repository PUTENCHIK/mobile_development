package com.example.fragmentweatherapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment
    var is_full: Boolean = false

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
}