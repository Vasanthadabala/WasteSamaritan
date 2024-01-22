package com.example.wastesamaritan.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutProjectScreen(navController:NavHostController) {
    Scaffold(
        topBar = { TopBar(name = "About Project", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            AboutProjectScreenComponent()
        }
    }
}

@Composable
fun AboutProjectScreenComponent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ){
        Text(
            text = "Waste Samaritan is an Internet-of-Things based waste monitoring system that tracks" +
                    "the effectiveness of segregation at source at a household level.This is enabled by " +
                    "the WS Collector to collect granular data and evidence on the quality of segregation " +
                    "of individual households by scanning a QR code installed on your gate.This will provide" +
                    "raedy-to-use data to the RWA memebers and Ward BBMP officials on prevalent Waste " +
                    "waste management practices in the ward for analytics and effective logistics planning for" +
                    "the ward.",
            color = MyColor.text,
            fontWeight = FontWeight.W500,
            fontSize = 17.sp
        )
        Text(text = "The citizens can also view the data specific to their households through the WS" +
                "Citizen app.You can also track the location of the collector duyring his waste Collection" +
                "rounds and get notified when he is entering your street!",
            color = MyColor.text,
            fontWeight = FontWeight.W500,
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8))
                .background(Color.Transparent) // Ensures the rounded corners are visible
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.person1),
                contentDescription = "Image",
            )
        }
        Column(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(
                text = "Supported by",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
            )
            Text(
                text = "Mr.C R Lakshminarayan(Gundanna)",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
            )
            Text(
                text = "B.E,",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
            )
            Text(
                text = "Corporator BBMP,",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
            )
            Text(
                text = "Ward No:112,",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
            )
            Text(
                text = "Domlur,Bangalore",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
            )
        }
    }
}