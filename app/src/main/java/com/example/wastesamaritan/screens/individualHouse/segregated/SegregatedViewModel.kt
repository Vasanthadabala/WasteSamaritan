package com.example.wastesamaritan.screens.individualHouse.segregated

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

data class ModelForCategoryData(
    val capturedImageUris: List<Uri>,
    val weightCards: MutableList<Double>,
    val rating: Double,
    val audio:File?
)

class SegregatedViewModel : ViewModel() {
    // Mutable LiveData for the selected category
    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    // Mutable map to hold category data for each category
    private val categoryDataMap = mutableMapOf<String, MutableLiveData<ModelForCategoryData>>()

    private val _audioFileSegregated = MutableLiveData<File?>()
    val audioFilSegregated: LiveData<File?> = _audioFileSegregated

    init {
        // Initialize selected category with a default value
        _selectedCategory.value = DEFAULT_CATEGORY

        // Initialize category data map with default values
        Categories.forEach { category ->
            categoryDataMap[category] = MutableLiveData(
                ModelForCategoryData(emptyList(), mutableListOf(),0.0,null)
            )
        }
        _audioFileSegregated.value = null
    }


    // Function to update the selected category
    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }

    // Function to get LiveData for category data of a specific category
    fun getCategoryData(category: String): LiveData<ModelForCategoryData>? {
        return categoryDataMap[category]
    }

    // Function to add captured image URI for a specific category
    fun addCapturedImageUri(category: String, uri: Uri) {
        val currentData = categoryDataMap[category]?.value ?: return
        val updatedUris = currentData.capturedImageUris + uri
        val newData = currentData.copy(capturedImageUris = updatedUris)
        categoryDataMap[category]?.value = newData
    }

    // Function to remove captured image URI for a specific category
    fun removeCapturedImageUri(category: String, uri: Uri) {
        val currentData = categoryDataMap[category]?.value ?: return
        val updatedUris = currentData.capturedImageUris - uri
        val newData = currentData.copy(capturedImageUris = updatedUris)
        categoryDataMap[category]?.value = newData
    }


    // Function to add weight card to a specific category
    fun addCategoryWeightCard(category: String, weight: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        if (!currentData.weightCards.contains(weight)) {
            val updatedWeightCards = currentData.weightCards.toMutableList().apply { add(weight) }
            val newData = currentData.copy(weightCards = updatedWeightCards)
            categoryDataMap[category]?.value = newData
        }
    }

    // Function to remove weight card from a specific category
    fun removeCategoryWeightCard(category: String, weight: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        val index = currentData.weightCards.indexOfFirst { it == weight }
        if (index != -1) {
            val updatedWeightCards = currentData.weightCards.toMutableList().apply { removeAt(index) }
            val newData = currentData.copy(weightCards = updatedWeightCards)
            categoryDataMap[category]?.value = newData
        }

    }

    // Function to update rating for a specific category
    fun updateCategoryRating(category: String, rating: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        val newData = currentData.copy(rating = rating)
        categoryDataMap[category]?.value = newData
    }

    // Function to update the audio file for all categories
    fun updateAudioFile(file: File?) {
        _audioFileSegregated.value = file
    }

    // Function to clear audio file for all categories
    fun clearAudioFile() {
        _audioFileSegregated.value?.let { file ->
            if (file.exists()) {
                file.delete()
            }
        }
        _audioFileSegregated.value = null
    }

    // Function to save audio file for all categories
    fun saveAudioFileForAllCategories(file: File?) {
        updateAudioFile(file)
    }

    // Function to clear audio file for all categories
    fun clearAudioFileForAllCategories() {
        clearAudioFile()
    }

    companion object {
        const val DEFAULT_CATEGORY = "Wet"
    }
}