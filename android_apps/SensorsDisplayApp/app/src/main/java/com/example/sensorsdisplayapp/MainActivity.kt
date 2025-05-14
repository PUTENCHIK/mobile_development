package com.example.sensorsdisplayapp

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sensorsdisplayapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "main_activity"
    private val sensor_types = mapOf(
        0 to Sensor.TYPE_AMBIENT_TEMPERATURE,
        1 to Sensor.TYPE_ACCELEROMETER,
        2 to Sensor.TYPE_HEART_RATE,
    )

    lateinit var binding: ActivityMainBinding
    lateinit var sensor_manager: SensorManager
    lateinit var listData: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val types = resources.getStringArray(R.array.sensor_types)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSensors.adapter = spinnerAdapter

        listData = arrayListOf()
        val recyclerAdapter = MyAdapter(LayoutInflater.from(this), listData)
        binding.sensorsList.layoutManager = LinearLayoutManager(this)
        binding.sensorsList.adapter = recyclerAdapter

        sensor_manager = getSystemService(SENSOR_SERVICE) as SensorManager

        binding.spinnerSensors.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                listData.clear()
                val sensor_list = sensor_manager.getSensorList(sensor_types[pos] ?: -1)
                if (sensor_list.isEmpty()) {
                    listData.add("empty")
                } else {
                    for (sensor in sensor_list) {
                        listData.add(sensor.name)
                    }
                }
                recyclerAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(TAG, "onNothingSelected")
            }
        }

    }
}