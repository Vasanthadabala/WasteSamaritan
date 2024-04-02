package com.example.wastesamaritan.data.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wastesamaritan.data.Categories

data class ModelForCategoryData(
    val capturedImageUris: List<Uri>,
    val totalWeight: Double,
    val weightCards: List<Double>,
    val rating: Double
)

class SegregatedViewModel : ViewModel() {
    // Mutable LiveData for the selected category
    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    // Mutable map to hold category data for each category
    private val categoryDataMap = mutableMapOf<String, MutableLiveData<ModelForCategoryData>>()

    init {
        // Initialize selected category with a default value
        _selectedCategory.value = DEFAULT_CATEGORY

        // Initialize category data map with default values
        Categories.forEach { category ->
            categoryDataMap[category] = MutableLiveData(
                ModelForCategoryData(emptyList(), 0.0, emptyList(),0.0)
            )
        }
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


    // Function to add weight to a specific category
    fun updateCategoryWeight(category: String, weight: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        val newData = currentData.copy(totalWeight = weight)
        categoryDataMap[category]?.value = newData
    }

    // Function to add weight card to a specific category
    fun addCategoryWeightCard(category: String, weight: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        val initialCard = currentData.weightCards.toMutableList()
        val updatedWeightCards = initialCard + weight
        val newData = currentData.copy(weightCards = updatedWeightCards)
        categoryDataMap[category]?.value = newData
    }

    // Function to remove weight card from a specific category
    fun removeCategoryWeightCard(category: String, weight: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        val initialCard = currentData.weightCards.toMutableList()
        val updatedWeightCards = initialCard - weight
        val newData = currentData.copy(weightCards = updatedWeightCards)
        categoryDataMap[category]?.value = newData
    }

    // Function to update rating for a specific category
    fun updateCategoryRating(category: String, rating: Double) {
        val currentData = categoryDataMap[category]?.value ?: return
        val newData = currentData.copy(rating = rating)
        categoryDataMap[category]?.value = newData
    }

    companion object {
        const val DEFAULT_CATEGORY = "Wet"
    }
}