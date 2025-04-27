package com.example.waiterapp

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.lifecycle.Observer
import com.example.waiterapp.databinding.ActivityMainBinding
import com.example.waiterapp.receivers.BatteryReceiver
import com.example.waiterapp.receivers.TimeReceiver

class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var receiving: Boolean = true

    private val br = BatteryReceiver()
    private val actionBattery = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    private val tr = TimeReceiver()
    private val actionTime = IntentFilter("android.intent.action.TIME_TICK")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receive()

        viewModel.minutes.observe(this, Observer { minutes ->
            val for_check = minutes%100
            val template: String = when {
                for_check%10 == 1 && for_check != 11 -> {
                    resources.getString(R.string.time_passed_template_1)
                }
                for_check%10 in 2..4 && for_check/10 != 1 -> {
                    resources.getString(R.string.time_passed_template_2_4)
                }
                else -> resources.getString(R.string.time_passed_template_5_9)
            }
            binding.timePassed.text = template.format(minutes)
        })


        viewModel.is_feeding.observe(this, Observer { is_feeding ->
            val color: Int
            val text: String
            when (is_feeding) {
                true -> {
                    color = resources.getColor(R.color.green)
                    text = resources.getString(R.string.is_feeding_true)
                }
                false -> {
                    color = resources.getColor(R.color.red)
                    text = resources.getString(R.string.is_feeding_false)
                }
            }
            binding.isFeeding.text = text
            binding.isFeeding.setTextColor(color)
        })

        viewModel.battery_level.observe(this, Observer { battery_level ->
            binding.waiterContent.background = getBatteryGradient(battery_level)
        })
    }

    fun receive() {
        registerReceiver(tr, actionTime)
        registerReceiver(br, actionBattery, RECEIVER_NOT_EXPORTED)
    }

    fun unreceive() {
        unregisterReceiver(tr)
        unregisterReceiver(br)
    }

    fun getBatteryColor(level: Int): Color {
        val red = ContextCompat.getColor(this, R.color.red)
        val green = ContextCompat.getColor(this, R.color.green)
        val percent: Float = level / 100f
        return when {
            level in 0..100 -> {
                Color.valueOf(
                    (green.red * percent + red.red * (1-percent)) / 255f,
                    (green.green * percent + red.green * (1-percent)) / 255f,
                    (green.blue * percent + red.blue * (1-percent)) / 255f,
                )
            }
            else -> Color.valueOf(red)
        }
    }

    fun getBatteryGradient(level: Int): GradientDrawable {
        val colors = IntArray(100)
        for (i in 0..99) {
            colors[i] = when {
                i > level -> Color.TRANSPARENT
                else -> getBatteryColor(level).toArgb()
            }
        }
        val gradient = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors)
        gradient.cornerRadius = 20f

        return gradient
    }

    fun handleButtonClick(view: View) {
        binding.button.text = when (receiving) {
            true -> {
                unreceive()
                resources.getString(R.string.button_alter)
            }
            false -> {
                receive()
                resources.getString(R.string.button_default)
            }
        }
        receiving = !receiving
    }
}