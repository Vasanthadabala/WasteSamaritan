package com.example.wastesamaritan.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.wastesamaritan.navigation.BottomBar
import com.example.wastesamaritan.navigation.bottomNavItems
import com.example.wastesamaritan.ui.theme.MyColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun DataScreen(navController: NavHostController){
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
            DataScreenComponent(navController)
        }
    }
}
@Composable
fun DataScreenComponent(navController: NavHostController){
//    val viewModel: RoomDatabaseViewModel = viewModel()
//    val notSegregatedData = viewModel.getNotSegregatedDataByScreenType().observeAsState(emptyList()).value
//    val segregatedData = viewModel.getSegregatedDataByScreenType().observeAsState(emptyList()).value

//    Column(
//        modifier = Modifier
//            .background(Color(0XFFE6EDf5)),
//    ) {
//        if (notSegregatedData.isNotEmpty() || segregatedData.isNotEmpty()) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//
//                    .weight(1f)
//            ) {
//                items(notSegregatedData + segregatedData) { item ->
//                    MenuDish(item = item, navController = navController, viewModel = viewModel)
//                }
//            }
//        } else {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .weight(1f),
//                contentAlignment = Alignment.Center
//            ) {
//                if (notSegregatedData.isEmpty() && segregatedData.isEmpty()) {
//                    Image(
//                        painter = painterResource(id = R.drawable.corrupted),
//                        contentDescription = null,
//                        modifier = Modifier.size(120.dp)
//                    )
//                }
//            }
//        }
//        FloatingActionButton(
//            onClick = {},
//            modifier = Modifier
//                .padding(top = 0.dp, bottom = 20.dp, start = 0.dp, end = 10.dp)
//                .align(Alignment.End)
//                .size(60.dp),
//            containerColor = Color(0XFFC8D1F7)
//        ) {
//            Text(
//                text = "+",
//                fontSize = 28.sp, fontWeight = FontWeight.Bold)
//        }
//    }
}
//@Composable
//fun MenuDish(item: ListEntity, navController: NavHostController, viewModel: ListViewModel) {
//    Card(
//        elevation = CardDefaults.cardElevation(1.dp),
//        shape = RoundedCornerShape(10.dp),
//        colors = CardDefaults.cardColors(Color.White),
//        modifier = Modifier
//            .padding(10.dp)
//            .clickable {
//                navController.navigate("${ItemDetails.route}/${item.id}")
//            }
//    ) {
//        Column(
//            modifier = Modifier.padding(10.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Item: ${item.name}",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.W800,
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .weight(1f)
//                )
//                Text(
//                    text = "Qty: ${item.quantity}",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.W800,
//                    modifier = Modifier.padding(5.dp)
//                )
//
//            }
//            Text(
//                text = "Ratings: ${item.rating}",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.W800,
//                modifier = Modifier.padding(5.dp)
//            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "Remarks: " +
//                            "${item.remarks.take(20)}${if (item.remarks.length>20) "..." else ""}",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.W800,
//                    modifier = Modifier
//                        .padding(5.dp, end = 10.dp)
//                        .weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.Rounded.Delete,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(5.dp)
//                        .align(Alignment.Bottom)
//                        .clickable {
//                            viewModel.deleteItem(item.id)
//                        }
//                )
//            }
//        }
//    }
//}