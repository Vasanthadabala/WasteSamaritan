package com.example.wastesamaritan.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastesamaritan.R
import com.example.wastesamaritan.ui.theme.MyColor

@Composable
fun FeedbackSection(
    onFeedbackButtonClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mic),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp, 40.dp)
                    .clip(shape = RoundedCornerShape(6.dp)),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = "Feedback",
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        Button(
            onClick = {onFeedbackButtonClicked},
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 1.dp,
                pressedElevation = 5.dp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(24),
            colors = ButtonDefaults.buttonColors(MyColor.primary)
        ) {
            Icon(
                imageVector = Icons.Rounded.Mic,
                contentDescription = "mic",
                modifier = Modifier.padding(1.dp)
            )
            Text(
                text = "Tap to speak",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}