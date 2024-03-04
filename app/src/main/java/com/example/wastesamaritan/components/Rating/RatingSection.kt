package com.example.wastesamaritan.components.Rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastesamaritan.R

@Composable
fun RatingSection(
    initialRating: Double,
    onRatingChanged: (Double) -> Unit
) {
    var rating by remember { mutableStateOf(initialRating) }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.customer_rating_illustration),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp, 40.dp)
                    .clip(shape = RoundedCornerShape(6.dp)),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Rating",
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                color = if (rating != 0.0) Color.Black else Color.Red
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            RatingBar(
                modifier = Modifier
                    .size(40.dp),
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                    onRatingChanged(newRating)
                }
            )
        }
    }
}