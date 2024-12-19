package com.example.peoplelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var people: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv_people = findViewById<ListView>(R.id.people)
//        val adapter = ArrayAdapter<String>(this, R.layout.item, people)
//        lv_people.adapter = adapter
    }

    fun addPersonToList(view: View) {
        val et_person = findViewById<EditText>(R.id.et_person)
        val name = et_person.text.toString()

        people.add(name)
        et_person.setText("")
        et_person.clearFocus()
    }
}