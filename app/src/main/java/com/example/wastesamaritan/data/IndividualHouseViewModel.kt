package com.example.wastesamaritan.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IndividualHouseViewModel : ViewModel() {
    private val _scannedResult = MutableLiveData<String>()
    val scannedResult: LiveData<String> = _scannedResult


    fun setScannedResult(result: String) {
        _scannedResult.value = result
    }
}