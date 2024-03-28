package com.example.wastesamaritan.components.weight

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@Composable
fun WeightInputSection(
    totalWeight: Double,
    initialWeight: Double,
    onWeightChange: (Double) -> Unit,
    onAddWeightClicked: (Double) -> Unit,
    initialWeightCards: List<Double>,
    onWeightCardRemove: (Double,Double) -> Unit,
    categoryColor: Color,
    textColor:Color
) {
    var weight by remember { mutableDoubleStateOf(initialWeight) }
    var weightCards by remember { mutableStateOf(initialWeightCards) }
    var totalWeightValue by remember { mutableDoubleStateOf(totalWeight) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Total Weight: $totalWeightValue kgs",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                color = MyColor.text,
                modifier = Modifier.padding(8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    singleLine = true,
                    value = if (weight == 0.0) "" else if (weight % 1 == 0.0) weight.toInt().toString() else weight.toString(),
                    onValueChange = {newWeight ->
                        if (newWeight.isNotEmpty()) {
                            val newValue = newWeight.toDoubleOrNull()
                            if (newValue != null) {
                                weight = newValue
                                onWeightChange(newValue)
                            }
                        }
                    },
                    placeholder = { Text(text = "Quantity") },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(52.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(18),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.DarkGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp
                    )
                )
                Button(
                    onClick = {
                        if (weight != 0.0) {
                            totalWeightValue += weight
                            weightCards += weight
                            onAddWeightClicked(weight) // Call the callback to handle adding weight
                            weight = 0.0
                        }
                    },
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 1.dp,
                        pressedElevation = 5.dp,
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                    shape = RoundedCornerShape(24),
                    colors = ButtonDefaults.buttonColors(categoryColor)
                ) {
                    Text(
                        text = "Add",
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        color = textColor,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
        ) {
            items(weightCards) { weight ->
                val iconSize = 16.dp
                val offsetInPx =
                    LocalDensity.current.run { ((iconSize - 5.dp) / 2).roundToPx() }
                Box(
                    modifier = Modifier
                        .padding(iconSize / 2)
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            text = weight.toString(),
                            color = Color.Black,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            val index = weightCards.indexOf(weight)
                            if (index != -1) {
                                weightCards = weightCards.toMutableList().apply { removeAt(index) }
                                totalWeightValue -= weight
                                onWeightCardRemove(weight, totalWeightValue)
                            }
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
                            modifier = Modifier.padding(0.dp),
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "close",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}