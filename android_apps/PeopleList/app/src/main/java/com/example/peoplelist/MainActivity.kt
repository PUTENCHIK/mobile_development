package com.example.peoplelist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var people: MutableList<String> = mutableListOf()
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv_people = findViewById<ListView>(R.id.people)
        adapter = ArrayAdapter<String>(this, R.layout.item, people)
        lv_people.adapter = adapter

        generateRandomData(30)
    }

    fun generateRandomData(n: Int) {
        val names = resources.getStringArray(R.array.names)
        val surnames = resources.getStringArray(R.array.surnames)
        for (i in 1..n) {
            addPerson("%s %s".format(names.random(), surnames.random()))
        }
    }

    fun addPerson(name: String) {
        people.add("%d. %s".format(people.size+1, name))
    }

    fun addPersonToList(view: View) {
        val et_person = findViewById<EditText>(R.id.et_person)
        val name = et_person.text.toString()

        if (name.length > 0) {
            addPerson(name)
            et_person.setText("")
            et_person.clearFocus()
            adapter.notifyDataSetChanged()
        } else {
            val toast: Toast = Toast.makeText(this,
                resources.getString(R.string.error_message),
                Toast.LENGTH_LONG)
            toast.show()
        }
    }
}