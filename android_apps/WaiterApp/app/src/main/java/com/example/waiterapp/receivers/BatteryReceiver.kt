package com.example.waiterapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.waiterapp.ViewModel

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val viewModel = (context as? ViewModelStoreOwner)?.viewModelStore?.let { store ->
            ViewModelProvider(store, ViewModelProvider.NewInstanceFactory()).get(ViewModel::class.java)
        }
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        viewModel?.setIsFeeding(status)

        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100)
        val battery: Float = level * 100 / scale.toFloat()
        viewModel?.setBatteryLevel(battery.toInt())
    }
}