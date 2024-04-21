package com.example.wastesamaritan.screens.individual_house.individual

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IndividualHouseViewModel : ViewModel() {
    // Define MutableLiveData to hold the scanned result
    private val _scannedResult = MutableLiveData<String>()

    // Expose LiveData to observe scanned result
    val scannedResult: LiveData<String> = _scannedResult

    // Maintain a list of scanned codes
    private val _scannedCodes = MutableLiveData<List<String>>(mutableListOf())

    // Expose LiveData to observe scanned codes
    val scannedCodes: LiveData<List<String>> = _scannedCodes

    // Function to set the scanned result and update LiveData
    fun setScannedResult(result: String) {
        _scannedResult.value = result
        // Update the list of scanned codes
        val codes = _scannedCodes.value ?: emptyList()
        if (!codes.contains(result)) {
            val updatedCodes = codes.toMutableList().apply { add(result) }
            _scannedCodes.value = updatedCodes
        }
    }
}