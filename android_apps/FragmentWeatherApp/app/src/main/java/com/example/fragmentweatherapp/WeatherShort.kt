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

class WeatherShort() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        val weatherShort = inflater.inflate(R.layout.fragment_weather_short, container, false)
        val tv_temp = weatherShort.findViewById<TextView>(R.id.temp_value)
        val tv_time = weatherShort.findViewById<TextView>(R.id.time_value)
        val API = resources.getString(R.string.API)

        CoroutineScope(Dispatchers.IO).launch {
            val data: Data
            try {
                data = DataGetter.request.getData(appid = API)

                val iv_icon = weatherShort.findViewById<ImageView>(R.id.weather_icon)
                val icon_url = resources.getString(R.string.icon_url).format(data.weather[0].icon)

                withContext(Dispatchers.Main) {
                    Picasso.get()
                        .load(icon_url)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.unknown)
                        .into(iv_icon)

                    tv_temp.text = String.format(resources.getString(R.string.temp_value_template), data.main.temp)
                    tv_time.text = activity.getNow()
                }

            } catch (e: Exception) {
                activity.showError(resources.getString(R.string.error_not_loaded))
            }
        }

        return weatherShort
    }
}