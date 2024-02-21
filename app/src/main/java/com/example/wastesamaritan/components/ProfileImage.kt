package com.example.wastesamaritan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.wastesamaritan.ui.theme.MyColor


@Composable
fun CircleProfileImage(firstLetter: String, size: Dp, fontsize: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .background(MyColor.primary, shape = CircleShape)
    ) {
        Text(
            text = firstLetter.toUpperCase(),
            color = Color.White,
            fontSize = fontsize.value.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}