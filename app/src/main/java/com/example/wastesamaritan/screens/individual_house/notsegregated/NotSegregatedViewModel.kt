package com.example.wastesamaritan.screens.individual_house.notsegregated

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class NotSegregatedViewModel : ViewModel() {
    //LiveData properties for the data relevant to the "Not Segregated" screen

    private val _weightCards = MutableLiveData<MutableList<Double>>(mutableListOf())
    val weightCards: LiveData<MutableList<Double>> = _weightCards

    private val _rating = MutableLiveData<Double>()
    val rating: LiveData<Double> = _rating


    private val _capturedImageUris = MutableLiveData<List<Uri>>()
    val capturedImageUris: LiveData<List<Uri>> = _capturedImageUris

    private val _audioFileNotSegregated = MutableLiveData<File?>()
    val audioFileNotSegregated: LiveData<File?> = _audioFileNotSegregated

    // Function to add captured image URI
    fun addCapturedImageUri(uri: Uri) {
        val currentList = _capturedImageUris.value ?: emptyList()
        _capturedImageUris.value = currentList + uri
    }

    // Function to remove captured image URI
    fun removeCapturedImageUri(uri: Uri) {
        val currentList = _capturedImageUris.value ?: emptyList()
        _capturedImageUris.value = currentList - uri
    }

    fun addWeightCard(weight: Double) {
        val currentList = _weightCards.value ?: mutableListOf()
        if (!currentList.contains(weight)) {
            val updatedList = currentList.toMutableList().apply { add(weight) }
            _weightCards.value = updatedList
        }
    }

    fun removeWeightCard(weight: Double) {
        val currentList = _weightCards.value ?: mutableListOf()
        val index = currentList.indexOf(weight
        )
        if (index != -1) {
            val updatedList = currentList.toMutableList()
            updatedList.removeAt(index)
            _weightCards.value = updatedList
        }
    }

    // Function to update rating
    fun updateRating(newRating: Double) {
        _rating.value = newRating
    }

    // Function to update audio file URI
    fun updateAudioFile(file: File?) {
        _audioFileNotSegregated.value = file
    }
}
