package com.example.wastesamaritan.screens.individualHouse.segregated

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.OutlinedReusableComponent
import com.example.wastesamaritan.components.image_capture.createImageFile
import com.example.wastesamaritan.data.roomdatabase.RoomDatabaseViewModel
import com.example.wastesamaritan.navigation.Home
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.screens.individualHouse.segregated.SegregatedViewModel.Companion.DEFAULT_CATEGORY
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SegregatedScreen(navController: NavHostController, viewModel: SegregatedViewModel, id:String) {

    val roomViewModel: RoomDatabaseViewModel = viewModel()

    Scaffold(
        topBar = { TopBar(name = "Segregated Screen", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            SegregatedScreenComponent(navController, viewModel, roomViewModel, id)
        }
    }
}

@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@Composable
fun SegregatedScreenComponent(navController: NavHostController, viewModel: SegregatedViewModel,roomViewModel: RoomDatabaseViewModel, id:String) {

    val context = LocalContext.current

    val selectedCategory by viewModel.selectedCategory.observeAsState(initial = DEFAULT_CATEGORY)

    // Observe the LiveData for selected category data
    val categoryData = viewModel.getCategoryData(selectedCategory)?.observeAsState()?.value

    val categoryColor = when (selectedCategory) {
        "Wet" -> Color(0XFF65B741)
        "Rejected" -> Color(0XFFFF0000)
        "Dry" -> Color(0XFF39A7FF)
        "Sanitary" -> Color.Magenta
        "E-Waste" -> Color.Yellow
        else -> Color.White
    }

    val textColor = when (selectedCategory) {
        "Wet" -> Color.White
        "Rejected" -> Color.White
        "Dry" -> Color.White
        "Sanitary" -> Color.White
        "E-Waste" -> Color.Black
        else -> Color.White
    }

    // Extract data from LiveData
    val capturedImageUris = categoryData?.capturedImageUris ?: emptyList()
    val weightCards = categoryData?.weightCards ?: mutableListOf()
    val rating = categoryData?.rating ?: 0.0
    val _audioFile = viewModel.audioFilSegregated.observeAsState(initial = null).value


    var currentUri: Uri? by remember { mutableStateOf(null) }


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

    // Extract data from LiveData
    val categoryDataMap = viewModel.categoryDataMap

    val allDataProvided = remember(categoryDataMap) {
        allDataProvidedForAllCategories(categoryDataMap.mapValues { it.value.value })
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.segregated_waste_illustration),
            contentDescription = "",
            modifier = Modifier
                .size(130.dp, 60.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillWidth
        )
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(6),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(3) ) {
                items(Categories) { category ->
                    WasteCategory(
                        category = category,
                        isSelected = selectedCategory == category,
                        onCategorySelected = { viewModel.setSelectedCategory(category) }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp)
        ) {
            OutlinedReusableComponent(
                context = context,
                capturedImageUris = capturedImageUris,
                onCameraClicked = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                onImageRemove = { removedUri ->
                    viewModel.removeCapturedImageUri(selectedCategory, removedUri)
                },
                onAddWeightClicked = { addedWeight ->
                    viewModel.addCategoryWeightCard(selectedCategory,addedWeight)
                },
                weightCards = weightCards,
                onWeightCardRemove = { removedWeight ->
                    viewModel.removeCategoryWeightCard(selectedCategory,removedWeight)
                },
                rating = rating,
                onRatingChanged = { newRating ->
                    viewModel.updateCategoryRating(selectedCategory, newRating)
                },
                categoryColor = categoryColor,
                textColor = textColor,
                audioFileInitial = _audioFile,
                onAudioFileAction = { newfile ->
                    viewModel.updateAudioFile(newfile)
                }
            )
        }
        Box(
            modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp)
        ) {
            Button(
                onClick = {
                    if (allDataProvided) {
                        categoryDataMap.forEach { (category, data) ->
                            data?.let { categoryData ->
                                roomViewModel.saveSegregatedData(
                                    id = id,
                                    category = category,
                                    capturedImageUris = categoryDataMap[category]?.value?.capturedImageUris ?: emptyList(),
                                    weightCards = categoryDataMap[category]?.value?.weightCards ?: mutableListOf(),
                                    rating = categoryDataMap[category]?.value?.rating ?: 0.0,
                                    screenType = "Segregated"
                                )
                            }
                        }
                        navController.navigate(Home.route) {
                            popUpTo(Home.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    } else {
                        Toast.makeText(context, "Provide data to all fields", Toast.LENGTH_SHORT).show()
                    }
                },
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
}

@Composable
fun WasteCategory(
    category: String,
    isSelected: Boolean,
    onCategorySelected: () -> Unit
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

    val textColor = when (category) {
        "Wet" -> if (isSelected) Color.White else Color.Black
        "Rejected" -> if (isSelected) Color.White else Color.Black
        "Dry" -> if (isSelected) Color.White else Color.Black
        "Sanitary" -> if (isSelected) Color.White else Color.Black
        "E-Waste" -> if (isSelected) Color.Black else Color.Black
        else -> Color.White
    }

    Button(
        onClick = onCategorySelected,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = RoundedCornerShape(32),
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Text(
            text = category,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = FontWeight.W800
        )
    }
}

private fun allDataProvidedForAllCategories(categoryDataMap: Map<String, ModelForCategoryData?>): Boolean {
    return categoryDataMap.all { (_, data) ->
        data?.rating != 0.0
    }
}