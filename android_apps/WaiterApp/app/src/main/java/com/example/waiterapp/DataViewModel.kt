package com.example.waiterapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private val _data = MutableLiveData(0)
    val data: LiveData<Int> = _data

    fun updateData(d: Int) {
        _data.postValue(d)
    }

    fun getData(): Int? {
        return data.value
    }

    fun increaseData() {
        _data.postValue((getData() ?: 0) + 1)
    }
}