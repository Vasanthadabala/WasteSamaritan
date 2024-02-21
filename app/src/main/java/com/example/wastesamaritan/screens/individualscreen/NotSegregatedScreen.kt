package com.example.wastesamaritan.screens.individualscreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.wastesamaritan.R
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date


@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun NotSegregatedScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(name = "Not Segregated Screen", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 55.dp)
        ) {
            NotSegregatedScreenComponent(navController)
        }
    }
}
@ExperimentalGlideComposeApi
@ExperimentalComposeUiApi
@Composable
fun NotSegregatedScreenComponent(navController: NavHostController) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    var quantity by remember { mutableIntStateOf(0) }
    var rating by remember { mutableDoubleStateOf(0.0) }
    var capturedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    var currentUri : Uri? = null

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
        if (result) {
            capturedImageUris = capturedImageUris + listOf(currentUri!!)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
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
                .size(145.dp, 68.dp)
                .clip(shape = RoundedCornerShape(6.dp)),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            OutlinedCard(
                elevation = CardDefaults.cardElevation(0.dp),
                shape = RoundedCornerShape(4),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .padding(12.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .size(100.dp)
                                .clickable {
                                    val permissionCheckResult =
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.CAMERA
                                        )
                                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                        val newImageFile = context.createImageFile()
                                        currentUri = FileProvider.getUriForFile(
                                            context,
                                            "${context.packageName}.provider",
                                            newImageFile
                                        )
                                        cameraLauncher.launch(currentUri)
                                    } else {
                                        // Request a permission
                                        permissionLauncher.launch(Manifest.permission.CAMERA)
                                    }
                                }
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.camera),
                                    contentDescription = "Camera"
                                )
                            }
                        }

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
                                            capturedImageUris = capturedImageUris - listOf(imageUri)
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
                    OutlinedCard(
                        elevation = CardDefaults.cardElevation(0.dp),
                        shape = RoundedCornerShape(6),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Total Weight: 10kgs",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W500,
                                color = MyColor.text,
                                modifier = Modifier.padding(8.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedTextField(
                                    singleLine = true,
                                    value = if (quantity == 0) "" else quantity.toString(),
                                    onValueChange = { quantity = it.toIntOrNull() ?: 0 },
                                    placeholder = { Text(text = "Quantity") },
                                    modifier = Modifier
                                        .padding(10.dp)
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
                                    onClick = {},
                                    elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 1.dp,
                                        pressedElevation = 5.dp,
                                    ),
                                    modifier = Modifier
                                        .padding(10.dp),
                                    shape = RoundedCornerShape(24),
                                    colors = ButtonDefaults.buttonColors(MyColor.primary)
                                ) {
                                    Text(
                                        text = "Add",
                                        textAlign = TextAlign.Center,
                                        fontSize = 22.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(0.dp)
                                    )
                                }
                            }
                        }
                    }
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
                                onRatingChanged = { rating = it }
                            )
                        }
                    }
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
                                color = if (rating != 0.0) Color.Black else Color.Red
                            )
                        }
                        Button(
                            onClick = {},
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
            }
        }
        Button(
            onClick = {},
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
            Text(
                text = "Save", textAlign = TextAlign.Center, fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

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

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}