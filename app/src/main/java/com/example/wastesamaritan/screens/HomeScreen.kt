package com.example.wastesamaritan.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.components.QrCodeScanner.BarcodeScanner
import com.example.wastesamaritan.data.IndividualHouseViewModel
import com.example.wastesamaritan.navigation.BottomBar
import com.example.wastesamaritan.navigation.DrawerNav
import com.example.wastesamaritan.navigation.HomeTopBar
import com.example.wastesamaritan.navigation.IndividualHouse
import com.example.wastesamaritan.navigation.bottomNavItems
import com.example.wastesamaritan.navigation.drawerItems
import com.example.wastesamaritan.ui.theme.MyColor
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController:NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var bottomSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: ""
    bottomSelectedItemIndex = bottomNavItems.indexOfFirst { it.title == currentRoute }

    var drawerSelectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val drawerCurrentRoute = navController.currentBackStackEntry?.destination?.route ?: ""
    drawerSelectedItemIndex = drawerItems.indexOfFirst { it.title == drawerCurrentRoute }

    ModalNavigationDrawer(
        drawerContent = {
            DrawerNav(
                drawerSelectedItemIndex = drawerSelectedItemIndex,
                onItemSelect = { drawerSelectedItemIndex = it },
                onCloseDrawer = { scope.launch { drawerState.close() } },
                navController = navController
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = { HomeTopBar(scope, drawerState, navController) },
            bottomBar = { BottomBar(navController = navController) }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MyColor.background)
                    .padding(top = 50.dp)
            ) {
                HomeScreenComponent(navController)
            }
        }
    }
}

@Composable
fun HomeScreenComponent(navController: NavHostController) {

    val viewModel:IndividualHouseViewModel = viewModel()

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)

    val username = sharedPreferences.getString("username", "")

    var codes = 0

    var buttonState by remember { mutableStateOf(ButtonState.START) }

    // Function to handle the button click
    val onButtonClick: () -> Unit = {
        when (buttonState) {
            ButtonState.START -> {
                buttonState = ButtonState.STOP
            }

            ButtonState.STOP -> {
                buttonState = ButtonState.START
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hi $username ",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = MyColor.text,
            modifier = Modifier.padding(10.dp)
        )
        OutlinedCard(
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, Color(0XFF39A7FF)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0XFFD6EDFF)),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(
                            top = 20.dp,
                            bottom = 10.dp,
                            start = 10.dp,
                            end = 10.dp
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dustbins_with_people),
                            contentDescription = "",
                            modifier = Modifier.size(100.dp, 50.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = "Individual House ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            color = MyColor.text,
                            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(
                            top = 20.dp,
                            bottom = 10.dp,
                            start = 10.dp,
                            end = 10.dp
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "$codes",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W600,
                                color = MyColor.text
                            )
                            Text(
                                text = "of 20",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W500,
                                color = MyColor.text,
                                modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                            )
                        }
                        Text(
                            text = "Today's Count",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            color = MyColor.text
                        )
                    }
                }

                Divider(color = Color(0XFF39A7FF), modifier = Modifier.fillMaxWidth())

                BarcodeScanner(
                    onScanResult = { result ->
                        viewModel.setScannedResult(result)
                        Toast.makeText(context, "Scanned result: $result", Toast.LENGTH_SHORT).show()
                        navController.navigate(IndividualHouse.route)
                    },
                    onPermissionDenied = {
                        Toast.makeText(context, "Camera permission denied. Cannot scan barcodes.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { onButtonClick() },
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 1.dp,
                        pressedElevation = 5.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 8.dp),
                    shape = RoundedCornerShape(24),
                    colors = when (buttonState) {
                        ButtonState.START -> ButtonDefaults.buttonColors(MyColor.primary)
                        ButtonState.STOP -> ButtonDefaults.buttonColors(Color.Red)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.QrCode,
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text(
                        text = buttonState.text,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }

    }
}

enum class ButtonState(val text: String) {
    START("Start"),
    STOP("Stop")
}