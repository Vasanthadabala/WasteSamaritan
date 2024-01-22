package com.example.wastesamaritan.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.navigation.Home
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@Composable
fun SigninScreen(navController: NavHostController) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current


    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val mail = email.text

    val sharedPreferences = context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putString("Mail", mail).apply()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(MyColor.background)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .padding(vertical = 20.dp, horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Welcome Back",
                fontSize = 28.sp,
                fontWeight = FontWeight.W500,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MyColor.primary)
                    .padding(30.dp)
            )
            Text(
                text = "Signin Information",
                fontSize = 22.sp,
                color = MyColor.text,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(start = 10.dp, bottom = 40.dp, top = 30.dp)
            )
            Text(
                text = "Username/CollectorID",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = MyColor.text,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
            )
            OutlinedTextField(
                singleLine = true,
                value = email,
                onValueChange = {
                    if (it.text.length <= 32) {
                        email = it
                    }
                },
                placeholder = { Text(text = "Username/CollectorID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, bottom = 10.dp, end = 15.dp, top = 10.dp),
                shape = RoundedCornerShape(18),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                leadingIcon = {
                    Box(modifier = Modifier.padding(start = 10.dp),
                        contentAlignment = Alignment.Center){
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "",
                            modifier = Modifier.size(26.dp))}},
//                trailingIcon = {
//                    Box(modifier = Modifier.padding(end = 10.dp),
//                        contentAlignment = Alignment.Center) {
//                        Icon(
//                            imageVector = Icons.Rounded.Error,
//                            contentDescription = "",
//                            modifier = Modifier.size(26.dp))
//                    }},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MyColor.primary,
                    unfocusedIndicatorColor = Color.DarkGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = MyColor.background,
                    cursorColor = Color.Black
                ),
                textStyle = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    color = MyColor.text
                )
            )
            Text(
                text = "Password",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = MyColor.text,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
            )
            OutlinedTextField(
                singleLine = true,
                value = password,
                onValueChange = {
                    if (it.text.length <= 32) {
                        password = it
                    }
                },
                placeholder = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, bottom = 10.dp, end = 15.dp, top = 10.dp),
                shape = RoundedCornerShape(18),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                leadingIcon = {
                    Box(modifier = Modifier.padding(start = 10.dp),
                        contentAlignment = Alignment.Center){
                        Icon(
                            imageVector = Icons.Rounded.Key,
                            contentDescription = "",
                            modifier = Modifier.size(26.dp))}},
                trailingIcon = {
                    Box(modifier = Modifier.padding(end = 10.dp),
                        contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.RemoveRedEye,
                            contentDescription = "",
                            modifier = Modifier.size(26.dp))
                    }},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MyColor.primary,
                    unfocusedIndicatorColor = Color.DarkGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = MyColor.background,
                    cursorColor = Color.Black
                ),
                textStyle = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    color = MyColor.text
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Button(
                onClick = {
                    if (password.text.isBlank() || email.text.isBlank()) {
                        Toast.makeText(
                            context,
                            "Signin Unsuccessful. Please enter all data",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Signin successful.",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Home.route)
                    }
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 5.dp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, bottom = 10.dp, end = 12.dp, top = 10.dp),
                shape = RoundedCornerShape(24),
                colors = ButtonDefaults.buttonColors(MyColor.primary)
            ) {
                Text(
                    text = "Login", textAlign = TextAlign.Center, fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(2.dp)
                )
            }
//            Row(
//                modifier = Modifier.padding(start = 80.dp, top = 10.dp)
//            ) {
//                Text(
//                    text = "Create Account",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Normal,
//                )
//                Text(
//                    text = "SignUp",
//                    fontSize = 18.sp,
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .padding(start = 10.dp)
//                )
//            }
        }
    }
}