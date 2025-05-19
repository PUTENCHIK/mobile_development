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
    lateinit var all_sensors: List<Sensor>

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

        all_sensors = sensor_manager.getSensorList(Sensor.TYPE_ALL)

        binding.spinnerSensors.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                listData.clear()
                val sortedSensors = sortSensorList(pos)
                if (sortedSensors.isEmpty()) {
                    listData.add("empty")
                } else {
                    for (sensor in sortedSensors) {
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

    fun sortSensorList(pos: Int): List<Sensor> {
        return when (pos) {
            0 -> all_sensors.filter {
                it.type == Sensor.TYPE_MAGNETIC_FIELD ||
                it.type == Sensor.TYPE_LIGHT ||
                it.type == Sensor.TYPE_PRESSURE ||
                it.type == Sensor.TYPE_RELATIVE_HUMIDITY ||
                it.type == Sensor.TYPE_AMBIENT_TEMPERATURE
            }
            1 -> all_sensors.filter {
                it.type == Sensor.TYPE_ACCELEROMETER ||
                it.type == Sensor.TYPE_GYROSCOPE ||
                it.type == Sensor.TYPE_PROXIMITY ||
                it.type == Sensor.TYPE_GRAVITY ||
                it.type == Sensor.TYPE_LINEAR_ACCELERATION ||
                it.type == Sensor.TYPE_ROTATION_VECTOR ||
                it.type == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR ||
                it.type == Sensor.TYPE_GYROSCOPE_UNCALIBRATED ||
                it.type == Sensor.TYPE_SIGNIFICANT_MOTION ||
                it.type == Sensor.TYPE_STEP_DETECTOR ||
                it.type == Sensor.TYPE_STEP_COUNTER ||
                it.type == Sensor.TYPE_MOTION_DETECT
            }
            2 -> all_sensors.filter {
                it.type == Sensor.TYPE_HEART_RATE ||
                it.type == Sensor.TYPE_HEART_BEAT ||
                it.type == Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT
            }
            else -> arrayListOf()
        }
    }
}