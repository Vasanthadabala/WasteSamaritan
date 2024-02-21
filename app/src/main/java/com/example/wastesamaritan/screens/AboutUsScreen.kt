package com.example.wastesamaritan.screens


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
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
fun AboutScreen(navController:NavHostController) {
    Scaffold(
        topBar = { TopBar(name = "About Us", navController = navController) },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MyColor.background)
                .padding(top = 60.dp)
        ) {
            AboutScreenComponent()
        }
    }
}

@Composable
fun AboutScreenComponent() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Column {
                Text(
                    text = "Marketing Head",
                    color = MyColor.text,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
                Card(
                    elevation = CardDefaults.cardElevation(3.dp),
                    shape = RoundedCornerShape(10),
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Wisvesh B.S.",
                            color = MyColor.text,
                            fontWeight = FontWeight.W500,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                        Row {
                            Text(
                                text = "Email ID:",
                                color = MyColor.text,
                                fontWeight = FontWeight.W500,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp)
                            )
                            ClickableText(
                                text = buildAnnotatedString {
                                    append("wisvesh@gmail.com")
                                },
                                onClick = { _ ->
                                    val email = "wisvesh@gmail.com"
                                    val intent =
                                        Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.padding(5.dp),
                                style = TextStyle(
                                    color = Color(0XFF39A7FF),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W500
                                )
                            )
                        }
                        Row {
                            Text(
                                text = "Phone:",
                                color = MyColor.text,
                                fontWeight = FontWeight.W500,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp)
                            )
                            ClickableText(
                                text = buildAnnotatedString {
                                    append("8147515627")
                                },
                                onClick = { _ ->
                                    val phoneNumber = "tel:8147515627"
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.padding(5.dp),
                                style = TextStyle(
                                    color = Color(0XFF39A7FF),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W500
                                )
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Operations Head",
                    color = MyColor.text,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Card(
                    elevation = CardDefaults.cardElevation(3.dp),
                    shape = RoundedCornerShape(10),
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Krishna A",
                            color = MyColor.text,
                            fontWeight = FontWeight.W500,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                        Row {
                            Text(
                                text = "Phone:",
                                color = MyColor.text,
                                fontWeight = FontWeight.W500,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp)
                            )
                            ClickableText(
                                text = buildAnnotatedString {
                                    append("9449565171")
                                },
                                onClick = { _ ->
                                    val phoneNumber1 = "tel:9449565171"
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber1))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.padding(5.dp),
                                style = TextStyle(
                                    color = Color(0XFF39A7FF),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W500
                                )
                            )
                            ClickableText(
                                text = buildAnnotatedString {
                                    append("9738888512")
                                },
                                onClick = { _ ->
                                    val phoneNumber2 = "tel:9738888512"
                                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber2))
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.padding(5.dp),
                                style = TextStyle(
                                    color = Color(0XFF39A7FF),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W500
                                )
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Developers",
                    color = MyColor.text,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Card(
                    elevation = CardDefaults.cardElevation(3.dp),
                    shape = RoundedCornerShape(10),
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Devi Priya Sarkar",
                            color = MyColor.text,
                            fontWeight = FontWeight.W500,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "Sharath Huddar",
                            color = MyColor.text,
                            fontWeight = FontWeight.W500,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
        Text(
            text = "Copyright © Waste Samaritans",
            color = Color.Gray,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}