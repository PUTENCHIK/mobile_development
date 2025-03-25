package com.example.currencytrackingapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.currencytrackingapp.adapter.CurrencyAdapter
import com.example.currencytrackingapp.adapter.CurrencyObject
import com.example.currencytrackingapp.databinding.ActivityMainBinding
import com.example.currencytrackingapp.service.CurrencyService
import java.time.LocalDateTime
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val TAG: String = "main_activity"
    lateinit var binding: ActivityMainBinding
    private var currencies: MutableList<CurrencyObject> = mutableListOf(
        CurrencyObject(R.drawable.ic_dollar_icon, "USD",
            "Доллар", 0.0f, 0.0f, LocalDateTime.now()),
        CurrencyObject(R.drawable.ic_euro_icon, "EUR",
            "Евро", 0.0f, 0.0f, LocalDateTime.now()),
        CurrencyObject(R.drawable.ic_ruble_icon, "RUB",
            "Рубль", 0.0f, 0.0f, LocalDateTime.now()),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CurrencyAdapter(this, R.layout.currency_layout, currencies)
        binding.currenciesList.adapter = adapter

        CurrencyService.dataViewModel.data.observe(this, Observer {newData ->
            Log.i(TAG, "New data gotten: $newData")
            for (currency in currencies) {
                val diff = when (currency.name) {
                    "USD" -> abs(newData.USD) - currency.quotation
                    "EUR" -> abs(newData.EUR) - currency.quotation
                    "RUB" -> abs(newData.RUB) - currency.quotation
                    else -> 0.0f
                }
                if (diff != 0f) {
                    currency.difference = when (currency.name) {
                        "USD" -> newData.USD - currency.quotation
                        "EUR" -> newData.EUR - currency.quotation
                        "RUB" -> newData.RUB - currency.quotation
                        else -> 0.0f
                    }
                    currency.quotation = when (currency.name) {
                        "USD" -> newData.USD
                        "EUR" -> newData.EUR
                        "RUB" -> newData.RUB
                        else -> 0.0f
                    }
                    currency.updated = LocalDateTime.now()
                }
            }
            adapter.notifyDataSetChanged()
        })

        CurrencyService.startService(this)
    }
}