package com.example.wastesamaritan.screens.individualscreen

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.CaptureImage.createImageFile
import com.example.wastesamaritan.components.OutlinedReusableComponent
import com.example.wastesamaritan.data.ViewModel.NotSegregatedViewModel
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor


@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun NotSegregatedScreen(navController: NavHostController, viewModel: NotSegregatedViewModel, id: String) {
    Scaffold(
        topBar = { TopBar(name = "Not Segregated Screen", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 55.dp)
        ) {
            NotSegregatedScreenComponent(navController,viewModel,id)
        }
    }
}
@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@Composable
fun NotSegregatedScreenComponent(navController: NavHostController, viewModel: NotSegregatedViewModel, id:String) {

    val context = LocalContext.current
    val categoryColor = MyColor.primary
    val textColor = Color.White

    var weight by remember { mutableStateOf(0.0) }

    // Observe LiveData properties from the ViewModel
    val totalWeight = viewModel.totalWeight.observeAsState(initial = 0.0).value
    val capturedImageUris = viewModel.capturedImageUris.observeAsState(initial = emptyList()).value
    val rating = viewModel.rating.observeAsState(initial = 0.0).value

    var weightCards by remember { mutableStateOf<List<Double>>(emptyList()) }

    var currentUri: Uri? by remember { mutableStateOf(null) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
        if (result) {
            currentUri?.let { uri ->
                viewModel.addCapturedImageUri(uri)
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
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.not_segregated_waste_illustration),
            contentDescription = "",
            modifier = Modifier
                .size(130.dp, 60.dp)
                .clip(shape = RoundedCornerShape(6.dp)),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp)
        ) {
            OutlinedReusableComponent(
                context = context,
                capturedImageUris = capturedImageUris,
                onCameraClicked = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                totalWeight = totalWeight,
                weight = weight,
                onWeightChange = { newWeight ->
                    weight=newWeight
                },
                onAddWeightClicked = { addedWeight ->
                    viewModel.updateTotalWeight(totalWeight + addedWeight)
                },
                weightCards = weightCards,
                onWeightCardRemove = { removedWeight, updatedTotalWeight ->
                    weightCards = weightCards.filter { it != removedWeight }
                    viewModel.updateTotalWeight(updatedTotalWeight)
                },
                rating = rating,
                onRatingChanged = { newRating ->
                    viewModel.updateRating(newRating)
                },
                onImageRemove = { removedUri ->
                    viewModel.removeCapturedImageUri(removedUri)
                },
                categoryColor = categoryColor,
                textColor = textColor
            )
        }
        Box(
            modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp)
        ) {
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
}
