package com.example.wastesamaritan.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.components.CircleProfileImage
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
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            ProfileScreenComponent()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProfileScreenComponent(){

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

    val username = sharedPreferences.getString("username","")
    val firstLetter = username?.take(1) ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircleProfileImage(firstLetter = firstLetter, size = 150.dp, fontsize = 60.dp)

        Text(
            text = "Name",
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            color = (Color(0XFF39A7FF)),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 12.dp, top = 12.dp)
        )
        OutlinedTextField(
            value = username.toString(),
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            enabled = false,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = MyColor.text,
            ),
            shape = RoundedCornerShape(18),
            leadingIcon = {
                Box(
                    modifier = Modifier.padding(start = 10.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        )
    }
}