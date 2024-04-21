package com.example.wastesamaritan.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.data.roomdatabase.NotSegregatedDataEntity
import com.example.wastesamaritan.data.roomdatabase.RoomDatabaseViewModel
import com.example.wastesamaritan.data.roomdatabase.SegregatedDataEntity
import com.example.wastesamaritan.navigation.BottomBar
import com.example.wastesamaritan.navigation.bottomNavItems
import com.example.wastesamaritan.ui.theme.MyColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NotSegregatedDataScreen(navController: NavHostController){
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
            NotSegregatedDataScreenComponent()
        }
    }
}
@Composable
fun NotSegregatedDataScreenComponent(){

    val viewModel: RoomDatabaseViewModel = viewModel()
    val notsegregatedData = viewModel.getNotSegregatedDataSortedById().observeAsState(initial = emptyList()).value

    Column(
        modifier = Modifier
            .background(Color(0XFFE6EDf5)),
    ) {
        if (notsegregatedData.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()

                    .weight(1f)
            ) {
                items(notsegregatedData) { item ->
                    DataShowing(data = item,viewModel)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (notsegregatedData.isEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.corrupted),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 0.dp, bottom = 20.dp, start = 0.dp, end = 10.dp)
                .align(Alignment.End)
                .size(60.dp),
            containerColor = Color(0XFFC8D1F7)
        ) {
            Text(
                text = "+",
                fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun DataShowing(data: NotSegregatedDataEntity,viewmodel:RoomDatabaseViewModel) {

    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { /* Define the action when the card is clicked */ }
    ) {
        Text(
            text = "Id: ${data.id}",
            fontSize = 18.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Ratings: ${data.rating}",
            fontSize = 18.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.padding(5.dp)
        )
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .align(alignment = Alignment.End)
                .clickable { viewmodel.deletenotsegregatedData(data.primaryKey)}
        )
    }
}