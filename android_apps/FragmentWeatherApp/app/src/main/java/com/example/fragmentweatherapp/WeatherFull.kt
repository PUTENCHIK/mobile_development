package com.example.fragmentweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class WeatherFull() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        val weatherFull = inflater.inflate(R.layout.fragment_weather_full, container, false)
        val tv_temp = weatherFull.findViewById<TextView>(R.id.temp_value)
        val tv_temp_fl = weatherFull.findViewById<TextView>(R.id.temp_feels_like_value)
        val tv_time = weatherFull.findViewById<TextView>(R.id.time_value)
        val tv_humidity = weatherFull.findViewById<TextView>(R.id.humidity_value)
        val tv_wind = weatherFull.findViewById<TextView>(R.id.wind_value)
        val tv_pressure = weatherFull.findViewById<TextView>(R.id.pressure_value)
        val tv_visibility = weatherFull.findViewById<TextView>(R.id.visibility_value)
        val API = resources.getString(R.string.API)

        CoroutineScope(Dispatchers.IO).launch {
            val data: Data
            try {
                withContext(Dispatchers.Main) {
                    activity.can_change = false
                }

                data = DataGetter.request.getData(appid = API)
                activity.no_internet_message_shown = false

                val iv_icon = weatherFull.findViewById<ImageView>(R.id.weather_icon)
                val icon_url = resources.getString(R.string.icon_url).format(data.weather[0].icon)

                withContext(Dispatchers.Main) {
                    Picasso.get()
                        .load(icon_url)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.unknown)
                        .into(iv_icon)

                    tv_temp.text = String.format(resources.getString(R.string.temp_value_template), data.main.temp)
                    tv_temp_fl.text = String.format(resources.getString(R.string.temp_value_template), data.main.feels_like)
                    tv_time.text = activity.getNow()
                    tv_humidity.text = String.format(resources.getString(R.string.humidity_template), data.main.humidity)
                    tv_wind.text = String.format(resources.getString(R.string.wind_template), data.wind.speed)
                    tv_pressure.text = String.format(resources.getString(R.string.pressure_template), data.main.pressure)
                    tv_visibility.text = String.format(resources.getString(R.string.visibility_template), (data.visibility/1000).toFloat())
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    if (!activity.no_internet_message_shown) {
                        activity.showError(resources.getString(R.string.error_not_loaded))
                    }
                    activity.no_internet_message_shown = true
                }
            } finally {
                withContext(Dispatchers.Main) {
                    activity.can_change = true
                }
            }
        }

        return weatherFull
    }
}