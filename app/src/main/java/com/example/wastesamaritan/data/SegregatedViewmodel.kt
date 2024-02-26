package com.example.wastesamaritan.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SegregatedViewModel : ViewModel() {

    // Mutable LiveData for the selected category
    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    // Mutable LiveData to hold the data for each category
    private val _categoryDataMap = MutableLiveData<Map<String, ModelForCategoryData>>(emptyMap())
    val categoryDataMap: LiveData<Map<String, ModelForCategoryData>> = _categoryDataMap

    init {
        // Initialize selected category with a default value
        _selectedCategory.value = DEFAULT_CATEGORY
    }

    // Function to update the selected category
    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }

    // Function to update the data for a specific category
    fun updateCategoryData(category: String, newData: ModelForCategoryData) {
        val updatedMap = _categoryDataMap.value?.toMutableMap() ?: mutableMapOf()
        updatedMap[category] = newData
        _categoryDataMap.value = updatedMap
    }

    companion object {
        const val DEFAULT_CATEGORY = "Dry"
    }
}

data class ModelForCategoryData(
    val capturedImageUris: List<String>,
    val totalWeight: Float,
    val rating: Float
)