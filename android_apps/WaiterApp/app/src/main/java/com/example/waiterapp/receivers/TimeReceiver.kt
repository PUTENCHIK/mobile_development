package com.example.waiterapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.waiterapp.ViewModel

class TimeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val viewModel = (context as? ViewModelStoreOwner)?.viewModelStore?.let { store ->
            ViewModelProvider(store, ViewModelProvider.NewInstanceFactory()).get(ViewModel::class.java)
        }
        viewModel?.updateMinutes()
    }
}