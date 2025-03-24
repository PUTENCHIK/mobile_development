package com.example.currencytrackingapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.currencytrackingapp.data.DataGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CurrencyService : Service() {

    private val TAG = "currency_service"
    private val checking_delay: Long = 60000

    override fun onCreate() {
        Log.i(TAG, "Service created")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service onStartCommand: $startId")

        CoroutineScope(Dispatchers.Main).launch {
            try {
                while (true) {
                    val response = withContext(Dispatchers.IO) {
                        DataGetter.request.getData(
                            fsym = "BTC",
                            tsyms = "USD,EUR,RUB"
                        )
                    }
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            dataViewModel.updateData(data)
                            Log.i(TAG, "Data updated: $data")
                        } else {
                            Log.e(TAG, "Empty response body")
                        }
                    } else {
                        Log.e(TAG, "Bad request")
                    }
                    delay(checking_delay)
                }
            } catch (e: IOException) {
                Log.e(TAG, "No internet connection")
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        }
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "Service destroyed")
        super.onDestroy()
    }

    companion object {
        val dataViewModel = DataViewModel()

        fun startService(context: Context) {
            val intent = Intent(context, CurrencyService::class.java)
            context.startService(intent)
        }
    }
}