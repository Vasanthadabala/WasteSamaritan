package com.example.wastesamaritan.screens.individualHouse.notsegregated

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class NotSegregatedViewModel : ViewModel() {
    //LiveData properties for the data relevant to the "Not Segregated" screen

    private val _weightCards = MutableLiveData<MutableList<Double>>()
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
        val currentList = _weightCards.value ?: mutableStateListOf()
        if (!currentList.contains(weight)) {
            val newdata = currentList.apply { add(weight)}
            _weightCards.value = newdata
        }
    }

    fun removeWeightCard(weight: Double) {
        val currentList = _weightCards.value ?: mutableStateListOf()
        val index = currentList.indexOfFirst { it == weight }
        if (index != -1) {
            val newdata = currentList.apply { removeAt(index)}
            _weightCards.value = newdata
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
