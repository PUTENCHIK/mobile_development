package com.example.waiterapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class TimeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,
            "получено сообщение: "+intent.toString(),
            Toast.LENGTH_SHORT).show()
        val viewModel = (context as? ViewModelStoreOwner)?.viewModelStore?.let { store ->
            ViewModelProvider(store, ViewModelProvider.NewInstanceFactory()).get(DataViewModel::class.java)
        }
        viewModel?.increaseData()
    }
}