package com.example.fragmentweatherapp.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentweatherapp.MainActivity
import com.example.fragmentweatherapp.R

class DataViewModel : ViewModel() {

    private val _data = MutableLiveData<Data>()
    lateinit var _context: Context

    fun setContext(c: Context) {
        _context = c
    }

    fun updateData(d: Data) {
        _data.value = d
    }

    fun getTemp() : String {
        val template = _context.resources.getString(R.string.temp_value_template)
        return String.format(template, _data.value?.main?.temp)
    }

    fun getTime() : String = (_context as MainActivity).getNow()

    fun getCity() : String = (_context as MainActivity).getCurrentCity()

    fun getHumidity() : String {
        val template = _context.resources.getString(R.string.humidity_template)
        return String.format(template, _data.value?.main?.humidity)
    }

    fun getWind() : String {
        val template = _context.resources.getString(R.string.wind_template)
        return String.format(template, _data.value?.wind?.speed)
    }

    fun getPressure() : String {
        val template = _context.resources.getString(R.string.pressure_template)
        return String.format(template, _data.value?.main?.pressure)
    }

    fun getVisibility() : String {
        val template = _context.resources.getString(R.string.visibility_template)
        return String.format(template, ((_data.value?.visibility ?: 0)/1000).toFloat())
    }
}


