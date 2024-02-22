package com.example.wastesamaritan.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBar(
    rating: Double = 0.0,
    stars:Int = 5,
    onRatingChanged: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    var isHalfStar = (rating % 1) !=0.0

    Row{
        for (index in 1..stars){
            Icon(
                modifier = modifier.clickable{onRatingChanged(index.toDouble())},
                contentDescription = null,
                tint = Color(0XFFFFC000),
                imageVector = if(index<=rating){
                    Icons.Rounded.Star
                }else{
                    if(isHalfStar){
                        isHalfStar = false
                        Icons.Rounded.StarHalf
                    }else{
                        Icons.Rounded.StarOutline
                    }
                }
            )
        }
    }
}