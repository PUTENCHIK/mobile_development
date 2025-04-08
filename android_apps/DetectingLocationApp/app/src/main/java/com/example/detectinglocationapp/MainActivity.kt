package com.example.detectinglocationapp

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.LocaleManagerCompat
import androidx.core.content.ContextCompat
import com.example.detectinglocationapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : AppCompatActivity(), LocationListener {
    lateinit var binding: ActivityMainBinding
    lateinit var locationManager: LocationManager
    val LOCATION_PERM_CODE = 2
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
        "EEE, dd MMMM, HH:mm:ss",
        Locale("ru")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestLocationPermission()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        displayStartLocation()
        displayStatus()
    }

    fun isPermissonGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun requestLocationPermission() {
        if (isPermissonGranted()) {
            Toast.makeText(
                this,
                "Определение местоположения разрешено",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERM_CODE
            )
        }
    }

    fun displayStartLocation() {
        if (isPermissonGranted()) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                5f,
                this
            )

            val prv = locationManager.getBestProvider(Criteria(), true)
            if (prv != null) {
                val location = locationManager.getLastKnownLocation(prv)
                if (location != null)
                    displayCoord(location.latitude, location.longitude)
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        displayCoord(location.latitude, location.longitude)
    }

    fun doubleToCoordinates(value: Double): String {
        val degrees: Double = value
        val minutes: Double = (value - value.toInt())*60
        val seconds: Double = (minutes - minutes.toInt())*60
        val template = resources.getString(R.string.coordinates_template)
        return template.format(degrees.toInt(), minutes.toInt(), seconds)
    }

    fun displayCoord(lat: Double, lng: Double) {
        binding.latValue.text = doubleToCoordinates(lat)
        binding.lngValue.text = doubleToCoordinates(lng)
        binding.lastUpdatedValue.text = formatter.format(LocalDateTime.now())
    }

    fun displayStatus() {
        when (isLocationEnabled()) {
            true -> {
                binding.statusValue.text = resources.getString(R.string.location_on)
                binding.statusValue.setTextColor(resources.getColor(R.color.good))
            }
            false -> {
                binding.statusValue.text = resources.getString(R.string.location_off)
                binding.statusValue.setTextColor(resources.getColor(R.color.bad))
            }
        }
    }
}