package com.example.fragmentweatherapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class MyPreferences(private val context: Context,
                    private val preferences_name: String) {

    fun get(): SharedPreferences {
        return context.getSharedPreferences(
            preferences_name,
            Activity.MODE_PRIVATE
        )
    }

    fun getLanguage(): String {
        return get().getString("lang", context.resources.getString(R.string.lang_default))!!
    }

    fun setLanguage(newLanguage: String) {
        with (get().edit()) {
            putString("lang", newLanguage)
            apply()
        }
    }

    fun getCityIndex(): Int {
        return get().getInt("city", 0)
    }

    fun setCity(cityIndex: Int) {
        with (get().edit()) {
            putInt("city", cityIndex)
            apply()
        }
    }

    fun getCity(): String {
        val cities = context.resources.getStringArray(R.array.cities)
        return cities[getCityIndex()]
    }

    fun getViewMode(): Boolean {
        // false - short mode, true - full mode
        return get().getBoolean("mode", false)
    }

    fun setViewMode(newMode: Boolean) {
        with (get().edit()) {
            putBoolean("mode", newMode)
            apply()
        }
    }

    fun invertViewMode() {
        setViewMode(!getViewMode())
    }
}