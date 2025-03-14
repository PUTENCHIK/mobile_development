package com.example.fragmentweatherapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentweatherapp.data.Data
import com.example.fragmentweatherapp.data.DataGetter
import com.example.fragmentweatherapp.MainActivity
import com.example.fragmentweatherapp.R
import com.example.fragmentweatherapp.data.DataViewModel
import com.example.fragmentweatherapp.databinding.FragmentWeatherShortBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.NumberFormatException

class WeatherShort : Fragment() {

    lateinit var binding: FragmentWeatherShortBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherShortBinding.inflate(layoutInflater)
        val activity = requireActivity() as MainActivity
        val weatherShort = binding.root
        val dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        dataViewModel.setContext(requireContext())

        val API = resources.getString(R.string.API)

        CoroutineScope(Dispatchers.IO).launch {
            var data: Data
            try {
                withContext(Dispatchers.Main) {
                    activity.can_change = false
                }

                data = DataGetter.request.getData(
                    q = activity.getCurrentCity(),
                    appid = API
                )
                activity.no_internet_message_shown = false

                val icon_url = resources.getString(R.string.icon_url).format(data.weather[0].icon)

                withContext(Dispatchers.Main) {
                    Picasso.get()
                        .load(icon_url)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.unknown)
                        .into(binding.weatherIcon)

                    dataViewModel.updateData(data)
                    binding.data = dataViewModel
                    binding.lifecycleOwner = viewLifecycleOwner
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Log.d("inner_error", "Message: ${e.message}")
                    if (!activity.no_internet_message_shown) {
                        activity.showMessage(resources.getString(R.string.error_not_loaded))
                    }
                    activity.no_internet_message_shown = true
                }
            } catch (e: NumberFormatException) {
                withContext(Dispatchers.Main) {
                    Log.d("inner_error", "Message: ${e.message}")
                    activity.showMessage(resources.getString(R.string.error_bad_data))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("inner_error", "Message: ${e.message}")
                    activity.showMessage(resources.getString(R.string.error_default))
                }
            } finally {
                withContext(Dispatchers.Main) {
                    activity.can_change = true
                }
            }
        }

        return weatherShort
    }
}