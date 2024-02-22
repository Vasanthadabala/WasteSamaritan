package com.example.wastesamaritan.screens.individualscreen

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.OutlinedReusableComponent
import com.example.wastesamaritan.components.createImageFile
import com.example.wastesamaritan.data.Categories
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor


@ExperimentalComposeUiApi
@ExperimentalGlideComposeApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SegregatedScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(name = "Segregated Screen", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            SegregatedScreenComponent(navController)
        }
    }
}
@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@Composable
fun SegregatedScreenComponent(navController: NavHostController){

    val context = LocalContext.current
    var selectedCategory by remember { mutableStateOf(Categories.first()) }

    var weight by remember { mutableStateOf(0) }
    var rating by remember { mutableStateOf(0.0) }
    var capturedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    var totalWeight by remember { mutableStateOf(0) }
    var weightCards by remember { mutableStateOf<List<Int>>(emptyList()) }

    var currentUri: Uri? = null

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
        if (result) {
            capturedImageUris = capturedImageUris + listOfNotNull(currentUri)
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
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(Categories) { category ->
                    WasteCategory(
                        category = category,
                        isSelected = selectedCategory == category,
                        onCategorySelected = { selectedCategory = category }
                    )
                }
            }
        }
        OutlinedReusableComponent(
            context = context,
            capturedImageUris = capturedImageUris,
            onCameraClicked = { permissionLauncher.launch(Manifest.permission.CAMERA) },
            totalWeight = totalWeight,
            weight = weight,
            onWeightChange = { newWeight -> weight = newWeight },
            onAddWeightClicked = { /* handle add weight clicked */ },
            weightCards = weightCards,
            onWeightCardRemove = { removedWeight, updatedTotalWeight ->
                weightCards = weightCards.filter { it != removedWeight }
                totalWeight = updatedTotalWeight // Update the total weight here
            },
            rating = rating,
            onRatingChanged = { newRating -> rating = newRating },
            onFeedbackButtonClicked = { /* handle feedback button clicked */ },
            onImageRemove = { removedUri ->
                capturedImageUris = capturedImageUris.filter { it != removedUri }
            }
        )
    }
}

@Composable
fun WasteCategory(category: String,isSelected:Boolean,onCategorySelected:()-> Unit) {
    Button(
        onClick = { onCategorySelected() },
        colors = ButtonDefaults.buttonColors(if (isSelected) Color.DarkGray else Color.LightGray),
        shape = RoundedCornerShape(32),
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp)
    ) {
        Text(
            text = category,
            fontSize = 12.sp,
            color = (if (isSelected) Color.White else Color(0XFF41544E)),
            fontWeight = FontWeight.W800
        )
    }
}