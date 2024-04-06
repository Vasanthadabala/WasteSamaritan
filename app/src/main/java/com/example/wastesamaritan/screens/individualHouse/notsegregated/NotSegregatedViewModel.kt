package com.example.wastesamaritan.screens.individualHouse.notsegregated

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class NotSegregatedViewModel : ViewModel() {
    //LiveData properties for the data relevant to the "Not Segregated" screen
    private val _totalWeight = MutableLiveData<Double>()
    val totalWeight: LiveData<Double> = _totalWeight

    private val _weightCards = MutableLiveData<List<Double>>()
    val weightCards: LiveData<List<Double>> = _weightCards

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

    // Function to update total weight
    fun updateTotalWeight(weight: Double) {
        _totalWeight.value = weight
    }

    fun addWeightCard(weight: Double) {
        val currentList = _weightCards.value ?: emptyList()
        _weightCards.value = currentList+weight
    }

    fun removeWeightCard(weight: Double) {
        val currentList = _weightCards.value ?: emptyList()
        val newList = currentList.toMutableList()
        newList.remove(weight)
        _weightCards.value = newList
    }

    // Function to update rating
    fun updateRating(newRating: Double) {
        _rating.value = newRating
    }

    // Function to update audio file URI
    fun updateAudioFile(file: File?) {
        _audioFileNotSegregated.value = file
    }

    // Function to clear audio file URI
    fun clearAudioFile() {
        _audioFileNotSegregated.value?.let { file ->
            if (file.exists()) {
                file.delete()
            }
        }
        _audioFileNotSegregated.value = null
    }
}
