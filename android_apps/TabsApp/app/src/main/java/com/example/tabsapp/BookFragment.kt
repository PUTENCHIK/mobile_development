package com.example.tabsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class BookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.book_layout, container, false)
        val rv = fragment.findViewById<RecyclerView>(R.id.books_recycler)
        rv.layoutManager = LinearLayoutManager(context)

        val booksData = readData()
        val adapter = BookAdapter(booksData)
        rv.adapter = adapter

        return fragment
    }

    fun readData(): List<BookData> {
        val inputStream = context?.resources?.openRawResource(R.raw.books)
        val reader = InputStreamReader(inputStream)
        val gson = Gson()
        val listType = object : TypeToken<List<BookData>>() {}.type

        return gson.fromJson(reader, listType)
    }
}