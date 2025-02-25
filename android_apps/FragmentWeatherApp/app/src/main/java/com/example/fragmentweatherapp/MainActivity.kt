package com.example.fragmentweatherapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment

    var is_full: Boolean = false
    var can_change: Boolean = false
    var no_internet_message_shown: Boolean = false
    var current_city: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchLocaleTo(getCurrentLanguage())

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        fm = supportFragmentManager
        ft = fm.beginTransaction()
        fr2 = WeatherFull()

        val fr = fm.findFragmentById(R.id.frame_container)
        if (fr == null) {
            fr1 = WeatherShort()
            fm.beginTransaction().add(R.id.frame_container, fr1).commit()
        } else {
            fr1 = fr
        }

        val container = findViewById<FrameLayout>(R.id.frame_container)
        container.setOnClickListener {
            if (can_change) {
                is_full = !is_full
                val ft = fm.beginTransaction()
                ft.replace(R.id.frame_container, getCurrentFragment()).commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.lang_russian -> {
                switchLocaleTo("ru")
                updateCurrentLanguage("ru")
                recreate()
                return true
            }
            R.id.lang_english -> {
                switchLocaleTo("en")
                updateCurrentLanguage("en")
                recreate()
                return true
            }
            else -> {
                showMessage(resources.getString(R.string.error_unknown_menu_item))
                return false
            }
        }
    }

    fun getSharedPreferences(): SharedPreferences {
        return this.getSharedPreferences(
            resources.getString(R.string.app_name),
            Activity.MODE_PRIVATE
        )
    }

    fun getCurrentFragment(): Fragment {
        return when (is_full) {
            false -> fr1
            true -> fr2
        }
    }

    fun showMessage(message: String) {
        val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    fun getNow(): String {
        val current: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
            "EEE, dd MMMM, HH:mm",
            Locale(getCurrentLanguage()))
        return current.format(formatter)
    }

    fun chooseCityHandler(view: View) {
        CitySelection(this, current_city).show(supportFragmentManager, "Tag")
    }

    fun getCurrentCity(): String {
        val cities = resources.getStringArray(R.array.cities)
        return cities[current_city]
    }

    fun updateCity(choice: Int) {
        current_city = choice
        val ft = fm.beginTransaction()
        ft.replace(R.id.frame_container, when (is_full) {
            false -> WeatherShort()
            true -> WeatherFull()
        }).commit()
    }

    fun switchLocaleTo(language: String) {
        val localeList = LocaleList(Locale(language))
        LocaleList.setDefault(localeList)
        resources.configuration.setLocales(localeList)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }

    fun getCurrentLanguage(): String {
        return getSharedPreferences().getString("lang", resources.getString(R.string.lang_default))!!
    }

    fun updateCurrentLanguage(new_lang: String) {
        with(getSharedPreferences().edit()) {
            putString("lang", new_lang)
            apply()
        }
    }
}