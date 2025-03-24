package com.example.currencytrackingapp.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencytrackingapp.data.ResponseData

class DataViewModel : ViewModel() {

    private val _data = MutableLiveData<ResponseData>()
    val data: LiveData<ResponseData> = _data

    fun updateData(d: ResponseData) {
        _data.postValue(d)
    }
}