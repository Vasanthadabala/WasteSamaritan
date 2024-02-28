import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ModelForCategoryData(
    val capturedImageUris: List<String>,
    val totalWeight: Double,
    val rating: Double
)

class SegregatedViewModel : ViewModel() {

    // Mutable LiveData for the selected category
    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    // Mutable LiveData to hold the data for each category
    private val _categoryDataMap = MutableLiveData<Map<String, ModelForCategoryData>>()
    val categoryDataMap: LiveData<Map<String, ModelForCategoryData>> = _categoryDataMap

    // Mutable map to hold captured image URIs for each category
    private val _capturedImageUrisMap = mutableMapOf<String, MutableLiveData<List<Uri>>>()

    // Mutable map to hold weight for each category
    private val _categoryWeightMap = mutableMapOf<String, MutableLiveData<Double>>()

    // Mutable map to hold rating for each category
    private val _categoryRatingMap = mutableMapOf<String, MutableLiveData<Double>>()

    init {
        // Initialize selected category with a default value
        _selectedCategory.value = DEFAULT_CATEGORY
        _categoryDataMap.value = emptyMap()
    }

    // Function to update the selected category
    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }

    // Function to update the data for a specific category
    fun updateCategoryData(category: String, newData: ModelForCategoryData, weight: Double) {
        val updatedMap = _categoryDataMap.value?.toMutableMap() ?: mutableMapOf()
        updatedMap[category] = newData
        _categoryDataMap.value = updatedMap
        _categoryWeightMap[category]?.value = weight
    }

    // Function to get data for a specific category
    fun getCategoryData(category: String): ModelForCategoryData? {
        return _categoryDataMap.value?.get(category)
    }

    // Function to get captured image URIs for a specific category
    fun getCapturedImageUris(category: String): LiveData<List<Uri>> {
        return _capturedImageUrisMap.getOrPut(category) { MutableLiveData(emptyList()) }
    }

    // Function to add captured image URI for a specific category
    fun addCapturedImageUri(category: String, uri: Uri) {
        val currentList = _capturedImageUrisMap.getOrPut(category) { MutableLiveData(emptyList()) }.value ?: emptyList()
        _capturedImageUrisMap[category]?.value = currentList + uri
    }

    // Function to remove captured image URI for a specific category
    fun removeCapturedImageUri(category: String, uri: Uri) {
        val currentList = _capturedImageUrisMap.getOrPut(category) { MutableLiveData(emptyList()) }.value ?: emptyList()
        _capturedImageUrisMap[category]?.value = currentList - uri
    }

    // Function to get weight for a specific category
    fun getCategoryWeight(category: String): LiveData<Double> {
        return _categoryWeightMap[category] ?: MutableLiveData(0.0)
    }

    // Function to get rating for a specific category
    fun getCategoryRating(category: String): LiveData<Double> {
        return _categoryRatingMap[category] ?: MutableLiveData(0.0)
    }

    // Function to update weight for a specific category
    fun updateCategoryWeight(category: String, weight: Double) {
        // Update weight for the category
        _categoryWeightMap[category]?.value = weight
    }

    // Function to update rating for a specific category
    fun updateCategoryRating(category: String, rating: Double) {
        // Update rating for the category
        _categoryRatingMap[category]?.value = rating
    }

    companion object {
        const val DEFAULT_CATEGORY = "Wet"
    }
}