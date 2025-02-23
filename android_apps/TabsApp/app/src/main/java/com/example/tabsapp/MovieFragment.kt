package com.example.tabsapp

import android.adservices.adselection.GetAdSelectionDataOutcome
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

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.movie_layout, container, false)
        val rv = fragment.findViewById<RecyclerView>(R.id.movies_recycler)
        rv.layoutManager = LinearLayoutManager(context)

        val moviesData = readData()
        val adapter = MovieAdapter(moviesData)
        rv.adapter = adapter

        return fragment
    }

    fun readData(): List<MovieData> {
        val inputStream = context?.resources?.openRawResource(R.raw.movies)
        val reader = InputStreamReader(inputStream)
        val gson = Gson()
        val listType = object : TypeToken<List<MovieData>>() {}.type

        return gson.fromJson(reader, listType)
    }
}