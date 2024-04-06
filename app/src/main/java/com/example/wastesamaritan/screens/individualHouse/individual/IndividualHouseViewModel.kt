package com.example.wastesamaritan.screens.individualHouse.individual

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IndividualHouseViewModel : ViewModel() {
    // Define MutableLiveData to hold the scanned result
    private val _scannedResult = MutableLiveData<String>()

    // Expose LiveData to observe scanned result
    val scannedResult: LiveData<String> = _scannedResult

    // Function to set the scanned result and update LiveData
    fun setScannedResult(result: String) {
        _scannedResult.value = result
    }
}