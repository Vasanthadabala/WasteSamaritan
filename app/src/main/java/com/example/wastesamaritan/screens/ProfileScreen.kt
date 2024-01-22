package com.example.wastesamaritan.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.navigation.TopBar
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController:NavHostController) {
    Scaffold(
        topBar = { TopBar(name = "Profile", navController = navController) },
    ) {
        Column(
            Modifier.fillMaxSize().background(MyColor.background).padding(top = 60.dp)
        ) {
            ProfileScreenComponent()
        }
    }
}

@Composable
fun ProfileScreenComponent(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Profile",
            color = MyColor.text,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp
        )
    }
}