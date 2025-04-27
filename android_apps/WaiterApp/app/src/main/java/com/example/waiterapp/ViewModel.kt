package com.example.waiterapp

import android.os.BatteryManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Duration
import java.time.LocalDateTime

class ViewModel : ViewModel() {
    private val startedAt = MutableLiveData(LocalDateTime.now())

    private val _minutes = MutableLiveData(0)
    val minutes: LiveData<Int> = _minutes

    private val _is_feeding = MutableLiveData(false)
    val is_feeding: LiveData<Boolean> = _is_feeding

    private val _battery_level = MutableLiveData(0)
    val battery_level: LiveData<Int> = _battery_level

    fun getMinutes(): Int? {
        return minutes.value
    }

    fun updateMinutes() {
        val difference = Duration.between(startedAt.value, LocalDateTime.now())
        _minutes.postValue(difference.toMinutes().toInt())
    }

    fun setIsFeeding(status: Int) {
        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            _is_feeding.postValue(true)
        } else {
            _is_feeding.postValue(false)
        }
    }

    fun setBatteryLevel(level: Int) {
        _battery_level.postValue(level)
    }
}