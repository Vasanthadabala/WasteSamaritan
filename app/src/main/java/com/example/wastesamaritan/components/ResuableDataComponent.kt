@file:OptIn(DelicateCoroutinesApi::class)

package com.example.wastesamaritan.components

import FeedbackSection
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.components.image_capture.CameraButton
import com.example.wastesamaritan.components.image_capture.CapturedImagesRow
import com.example.wastesamaritan.components.image_capture.createImageFile
import com.example.wastesamaritan.components.rating.RatingSection
import com.example.wastesamaritan.components.weight.WeightInputSection
import kotlinx.coroutines.DelicateCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalGlideComposeApi
@Composable
fun OutlinedReusableComponent(
    context: Context,
    capturedImageUris: List<Uri>,
    onCameraClicked: (Uri) -> Unit,
    totalWeight: Double,
    initialWeight: Double,
    onWeightChange: (Double) -> Unit,
    onAddWeightClicked: (Double) -> Unit,
    weightCards: List<Double>,
    onWeightCardRemove: (Double, Double) -> Unit,
    rating: Double,
    onRatingChanged: (Double) -> Unit,
    onImageRemove: (Uri) -> Unit,
    categoryColor: Color,
    textColor: Color
) {

    Column {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(3),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    CameraButton(
                        context = context,
                        currentUri = capturedImageUris.lastOrNull(),
                        onCameraClicked = onCameraClicked,
                        createImageFile = context::createImageFile
                    )
                    CapturedImagesRow(
                        capturedImageUris = capturedImageUris,
                        onImageRemove = onImageRemove
                    )
                }
                WeightInputSection(
                    totalWeight = totalWeight,
                    initialWeight = initialWeight,
                    onWeightChange = onWeightChange,
                    onAddWeightClicked = onAddWeightClicked,
                    initialWeightCards = weightCards,
                    onWeightCardRemove = onWeightCardRemove,
                    categoryColor = categoryColor,
                    textColor = textColor
                )
                RatingSection(
                    initialRating = rating,
                    onRatingChanged = onRatingChanged
                )
            }
        }
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(6),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .padding(5.dp)
        ) {
            FeedbackSection()
        }
    }
}