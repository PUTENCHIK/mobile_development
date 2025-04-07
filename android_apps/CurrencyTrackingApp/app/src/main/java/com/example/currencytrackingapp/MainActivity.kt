package com.example.currencytrackingapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.currencytrackingapp.adapter.CurrencyAdapter
import com.example.currencytrackingapp.adapter.CurrencyObject
import com.example.currencytrackingapp.databinding.ActivityMainBinding
import com.example.currencytrackingapp.service.CurrencyService
import java.time.LocalDateTime
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val TAG: String = "main_activity"
    private val CHANNEL_ID: String = "CurrencyTracking"
    lateinit var binding: ActivityMainBinding
    private var currencies: MutableList<CurrencyObject> = mutableListOf(
        CurrencyObject(R.drawable.ic_dollar_icon, "USD",
            "Доллар", 0.0f, 0.0f, LocalDateTime.now()),
        CurrencyObject(R.drawable.ic_euro_icon, "EUR",
            "Евро", 0.0f, 0.0f, LocalDateTime.now()),
        CurrencyObject(R.drawable.ic_ruble_icon, "RUB",
            "Рубль", 0.0f, 0.0f, LocalDateTime.now()),
    )

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d(TAG, "Notification permission granted")
            Toast.makeText(this, "Уведомления разрешены", Toast.LENGTH_LONG)
                .show()
        } else {
            Log.d(TAG, "Notification permission denied")
            Toast.makeText(this, "Уведомления запрещены", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CurrencyAdapter(this, R.layout.currency_layout, currencies)
        binding.currenciesList.adapter = adapter

        requestNotificationPermission()
        createNotificationChannel()

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
                    createNotification()
                }
            }
            adapter.notifyDataSetChanged()
        })

        CurrencyService.startService(this)
    }

    fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "Notification permission already granted")
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                Log.d(TAG, "Showing notification permission rationale")
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)

            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val name = "MyChannel"
                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val channel = NotificationChannel(CHANNEL_ID, name, importance)
                channel.apply {
                    description = "No description"
                }
                notificationManager.createNotificationChannel(channel)
                Log.d(TAG, "Channel ${CHANNEL_ID} has been created")
            } else {
                Log.w(TAG, "Channel has already been")
            }
        }
    }

    fun createNotification() {
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bitcoin_icon)
            .setContentTitle("Отслеживание криптовалюты")
            .setContentText("Котировки изменились")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent)

        with (NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }
}