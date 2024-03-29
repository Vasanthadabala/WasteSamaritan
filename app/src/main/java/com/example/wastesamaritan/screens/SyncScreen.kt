package com.example.wastesamaritan.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.navigation.BottomBar
import com.example.wastesamaritan.navigation.bottomNavItems
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SyncScreen(navController:NavHostController) {

    var bottomSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: ""
    bottomSelectedItemIndex = bottomNavItems.indexOfFirst { it.title == currentRoute }

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
        ) {
            SyncScreenComponent()
        }
    }
}

@Composable
fun SyncScreenComponent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(0),
            colors = CardDefaults.cardColors(MyColor.background),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Sync",
                color = MyColor.text,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 5.dp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 60.dp),
                shape = RoundedCornerShape(24),
                colors = ButtonDefaults.buttonColors(Color(0XFF87C4FF))
            ) {
                Icon(
                    imageVector = Icons.Rounded.QrCode2,
                    contentDescription = "",
                    tint = Color.Black
                )
                Text(
                    text = " Scan",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = MyColor.text,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}