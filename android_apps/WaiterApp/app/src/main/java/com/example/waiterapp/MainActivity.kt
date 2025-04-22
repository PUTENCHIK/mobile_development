package com.example.waiterapp

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.waiterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val dataViewModel: DataViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receiver = TimeReceiver()
        val intentFilterAvia = IntentFilter("android.intent.action.TIME_TICK")
        registerReceiver(receiver, intentFilterAvia)

        dataViewModel.data.observe(this, Observer { data ->
            binding.timePassed.text = "$data минут"
        })
    }
}