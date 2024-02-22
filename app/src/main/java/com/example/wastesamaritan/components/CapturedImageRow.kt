package com.example.wastesamaritan.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@ExperimentalGlideComposeApi
@Composable
fun CapturedImagesRow(
    capturedImageUris: List<Uri>,
    onImageRemove: (Uri) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(5.dp)
            .width(300.dp)
    ) {
        items(capturedImageUris) { imageUri ->
            val iconSize = 24.dp
            val offsetInPx =
                LocalDensity.current.run { ((iconSize - 5.dp) / 2).roundToPx() }
            Box(
                modifier = Modifier
                    .padding(iconSize / 2)
            ) {
                Card {
                    GlideImage(
                        model = imageUri,
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(95.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
                IconButton(
                    onClick = {
                        onImageRemove(imageUri)
                    },
                    modifier = Modifier
                        .offset {
                            IntOffset(x = +offsetInPx, y = -offsetInPx)
                        }
                        .clip(CircleShape)
                        .background(Color(0xFFEA4141))
                        .size(iconSize)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        modifier = Modifier.padding(3.dp),
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "close",
                        tint = Color.White
                    )
                }
            }
        }
    }
}