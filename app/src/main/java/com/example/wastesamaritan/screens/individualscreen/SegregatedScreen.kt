package com.example.wastesamaritan.screens.individualscreen

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.OutlinedReusableComponent
import com.example.wastesamaritan.components.createImageFile
import com.example.wastesamaritan.data.Categories
import com.example.wastesamaritan.data.ModelForCategoryData
import com.example.wastesamaritan.data.SegregatedViewModel
import com.example.wastesamaritan.data.SegregatedViewModel.Companion.DEFAULT_CATEGORY
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor


@ExperimentalComposeUiApi
@ExperimentalGlideComposeApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SegregatedScreen(navController: NavHostController,viewModel:SegregatedViewModel) {
    Scaffold(
        topBar = { TopBar(name = "Segregated Screen", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            SegregatedScreenComponent(navController, viewModel)
        }
    }
}
@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@Composable
fun SegregatedScreenComponent(navController: NavHostController,viewModel:SegregatedViewModel){

    val context = LocalContext.current
//    var selectedCategory by remember { mutableStateOf(Categories.first()) }
    val selectedCategory by viewModel.selectedCategory.observeAsState(initial = DEFAULT_CATEGORY)
    val categoryDataMap by viewModel.categoryDataMap.observeAsState(initial = emptyMap())
    val categoryData = categoryDataMap[selectedCategory] ?: ModelForCategoryData(emptyList(), 0.0, 0.0)

    // Observe the LiveData for captured image URIs
    val capturedImageUris by viewModel.getCapturedImageUris(selectedCategory).observeAsState(initial = emptyList())

    // Extract weight and rating from category data
    var weight by remember { mutableStateOf(categoryData.totalWeight) }
    var rating by remember { mutableStateOf(categoryData.rating) }

    var totalWeight by remember { mutableStateOf(0.0) }
    var weightCards by remember { mutableStateOf<List<Double>>(emptyList()) }

    var currentUri: Uri? = null

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
        if (result) {
            currentUri?.let { uri ->
                viewModel.addCapturedImageUri(selectedCategory, uri)
            }
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isPermissionGranted ->
        if (isPermissionGranted) {
            val newImageFile = context.createImageFile()
            currentUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                newImageFile
            )
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(currentUri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.segregated_waste_illustration),
            contentDescription = "",
            modifier = Modifier
                .size(145.dp, 68.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillWidth
        )
        Card(
            elevation = CardDefaults.cardElevation(3.dp),
            shape = RoundedCornerShape(10),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(Categories) { category ->
                    WasteCategory(
                        category = category,
                        isSelected = selectedCategory == category,
                        onCategorySelected = { viewModel.setSelectedCategory(category)  }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            OutlinedReusableComponent(
                context = context,
                capturedImageUris =  viewModel.getCapturedImageUris(selectedCategory).observeAsState(initial = emptyList()).value,
                onCameraClicked = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                totalWeight = totalWeight,
                weight = weight,
                onWeightChange = { newWeight ->
                    weight = newWeight
                    viewModel.updateCategoryData(
                        selectedCategory,
                        ModelForCategoryData(capturedImageUris.map { it.toString() },
                            totalWeight,
                            rating),
                        newWeight
                    )
                },
                onAddWeightClicked = { /* handle add weight clicked */ },
                weightCards = weightCards,
                onWeightCardRemove = { removedWeight, updatedTotalWeight ->
                    weightCards = weightCards.filter { it != removedWeight }
                    totalWeight = updatedTotalWeight// Update the total weight here
                    weight = totalWeight // Convert totalWeight to Double
                    viewModel.updateCategoryData(
                        selectedCategory,
                        ModelForCategoryData(capturedImageUris.map { it.toString() },
                            totalWeight,
                            rating
                        ),updatedTotalWeight)
                },
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                    viewModel.updateCategoryData(selectedCategory, ModelForCategoryData(capturedImageUris.map { it.toString() }, totalWeight, rating),weight)
                },
                onFeedbackButtonClicked = { /* handle feedback button clicked */ },
                onImageRemove = { removedUri ->
                    viewModel.removeCapturedImageUri(selectedCategory, removedUri)
                    viewModel.updateCategoryData(selectedCategory, ModelForCategoryData(capturedImageUris.map { it.toString() }, totalWeight.toDouble(), rating),weight)
                }
            )
        }

        Button(
            onClick = { /* handle save button click */ },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 1.dp,
                pressedElevation = 5.dp
            ),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24),
            colors = ButtonDefaults.buttonColors(MyColor.primary)
        ) {
            Text(
                text = "Save",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Composable
fun WasteCategory(
    category: String,
    isSelected:Boolean,
    onCategorySelected:()-> Unit
) {
    val backgroundColor = when (category) {
        "Wet" -> if (isSelected) Color(0XFF65B741) else Color.White
        "Rejected" -> if (isSelected) Color(0XFFFF0000) else Color.White
        "Dry" -> if (isSelected) Color(0XFF39A7FF) else Color.White
        "Sanitary" -> if (isSelected) Color.Magenta else Color.White
        "E-Waste" -> if (isSelected) Color.Yellow else Color.White
        else -> Color.White
    }

    val borderColor = when (category) {
        "Wet" -> Color(0XFF65B741)
        "Rejected" -> Color(0XFFD80032)
        "Dry" -> Color(0XFF39A7FF)
        "Sanitary" -> Color.Magenta
        "E-Waste" -> Color.Yellow
        else -> Color.White
    }

    Button(
        onClick = onCategorySelected,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = RoundedCornerShape(32),
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
        border = BorderStroke(1.dp, borderColor )
    ) {
        Text(
            text = category,
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.W800
        )
    }
}