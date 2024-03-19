package com.example.wastesamaritan.screens.individualscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.data.IndividualHouseViewModel
import com.example.wastesamaritan.navigation.Home
import com.example.wastesamaritan.navigation.NotSegregated
import com.example.wastesamaritan.navigation.Segregated
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun IndividualHouseScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(name = "Individual House", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            IndividualHouseScreenComponent(navController)
        }
    }
}

@Composable
fun IndividualHouseScreenComponent(navController: NavHostController){

    val viewModel: IndividualHouseViewModel = viewModel()
    val scannedResultText by viewModel.scannedResult.observeAsState("")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dustbins_with_people),
            contentDescription = "",
            modifier = Modifier
                .size(140.dp, 68.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillWidth
        )
        Card(
            elevation = CardDefaults.cardElevation(3.dp),
            shape = RoundedCornerShape(15),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp, horizontal = 10.dp)
        )
        {
            Text(
                text = "House Number: E.G.${scannedResultText}",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Text(
            text = "Select Waste Type",
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedCard(
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .clickable { navController.navigate(Home.route) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_waste_illustration),
                    contentDescription = "No waste",
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    text = "No Waste",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        OutlinedCard(
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color(0XFF65B741)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .clickable { navController.navigate(Segregated.route) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.segregated_waste_illustration),
                    contentDescription = "Segregated",
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    text = "Segregated",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        OutlinedCard(
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .clickable { navController.navigate(NotSegregated.route) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.not_segregated_waste_illustration),
                    contentDescription = "Not Segregated",
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    text = "Not Segregated",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}