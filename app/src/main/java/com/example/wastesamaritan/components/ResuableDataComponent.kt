package com.example.wastesamaritan.components

import FeedbackSection
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
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
import com.example.wastesamaritan.components.CaptureImage.CameraButton
import com.example.wastesamaritan.components.CaptureImage.CapturedImagesRow
import com.example.wastesamaritan.components.CaptureImage.createImageFile
import com.example.wastesamaritan.components.Rating.RatingSection
import com.example.wastesamaritan.components.Weight.WeightInputSection

@ExperimentalComposeUiApi
@ExperimentalGlideComposeApi
@Composable
fun OutlinedReusableComponent(
    context: Context,
    capturedImageUris: List<Uri>,
    onCameraClicked: (Uri) -> Unit,
    totalWeight: Double,
    weight: Double,
    onWeightChange: (Double) -> Unit,
    onAddWeightClicked: () -> Unit,
    weightCards: List<Double>,
    onWeightCardRemove: (Double,Double) -> Unit,
    rating: Double,
    onRatingChanged: (Double) -> Unit,
    onImageRemove:(Uri)-> Unit,
    categoryColor: Color,
    textColor:Color
) {

    var mutableWeightCards by remember { mutableStateOf(weightCards) }
    var mutableTotalWeight by remember { mutableStateOf(totalWeight) }

    OutlinedCard(
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(4),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
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
                CapturedImagesRow( capturedImageUris = capturedImageUris, onImageRemove = onImageRemove)
            }
            WeightInputSection(
                totalWeight = totalWeight,
                initialWeight = weight,
                onWeightChange = onWeightChange,
                onAddWeightClicked = {
                    mutableTotalWeight += weight
                    mutableWeightCards += weight
                    onAddWeightClicked()
                },
                initialWeightCards = mutableWeightCards,
                onWeightCardRemove = { removedWeight, _ ->
                    mutableWeightCards = mutableWeightCards.filter { it != removedWeight }
                    mutableTotalWeight -= removedWeight
                    onWeightCardRemove(removedWeight, mutableTotalWeight)
                },
                categoryColor = categoryColor,
                textColor = textColor
            )
            RatingSection(initialRating = rating, onRatingChanged = onRatingChanged)
            FeedbackSection()
        }
    }
}
