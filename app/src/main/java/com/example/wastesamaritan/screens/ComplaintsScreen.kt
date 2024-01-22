package com.example.wastesamaritan.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.navigation.BottomBar
import com.example.wastesamaritan.navigation.MainTopBar
import com.example.wastesamaritan.navigation.bottomNavItems
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComplaintsScreen(navController:NavHostController) {

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: ""
    selectedItemIndex = bottomNavItems.indexOfFirst { it.title == currentRoute }

        Scaffold(
            bottomBar = { BottomBar(navController = navController)}
        ) {
            Column(
                Modifier.fillMaxSize().background(MyColor.background).padding(top = 60.dp)
            ) {
                ComplaintsScreenComponent()
            }
        }
    }

@Composable
fun ComplaintsScreenComponent(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Complaints",
            color = MyColor.text,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp
        )
    }
}