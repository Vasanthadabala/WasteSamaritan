package com.example.wastesamaritan.navigation

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.data.SegregatedViewModel
import com.example.wastesamaritan.screens.AboutProjectScreen
import com.example.wastesamaritan.screens.AboutScreen
import com.example.wastesamaritan.screens.ComplaintsScreen
import com.example.wastesamaritan.screens.HomeScreen
import com.example.wastesamaritan.screens.individualscreen.IndividualHouseScreen
import com.example.wastesamaritan.screens.MyRatingsScreen
import com.example.wastesamaritan.screens.ProfileScreen
import com.example.wastesamaritan.screens.QrScanScreen
import com.example.wastesamaritan.screens.SigninScreen
import com.example.wastesamaritan.screens.SyncScreen
import com.example.wastesamaritan.screens.individualscreen.NotSegregatedScreen
import com.example.wastesamaritan.screens.individualscreen.SegregatedScreen

@ExperimentalGlideComposeApi
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun MyNavigation(context:Context,viewModel: SegregatedViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Segregated.route )//destination(context)
    {
        composable(Signin.route){
            SigninScreen(navController)
        }
        composable(Home.route){
            HomeScreen(navController)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
        composable(AboutUs.route){
            AboutScreen(navController)
        }
        composable(AboutProject.route){
            AboutProjectScreen(navController)
        }
        composable(MyRatings.route){
            MyRatingsScreen(navController)
        }
        composable(Complaints.route){
            ComplaintsScreen(navController)
        }
        composable(Sync.route){
            SyncScreen(navController)
        }
        composable(QrScan.route){
            QrScanScreen(navController)
        }
        composable(IndividualHouse.route){
            IndividualHouseScreen(navController)
        }
        composable(NotSegregated.route){
            NotSegregatedScreen(navController)
        }
        composable(Segregated.route){
            SegregatedScreen(navController,viewModel)
        }
    }
}

fun destination(context: Context):String{
    val sharedPreferences = context.getSharedPreferences("MY_PRE",Context.MODE_PRIVATE)
    val isSignedin = sharedPreferences.getBoolean("isSignedin",false)
    val isSignedup = sharedPreferences.getBoolean("isSignedup",false)
    if(isSignedin || isSignedup)
    {
        return Home.route
    }
    else
    {
        return Signin.route
    }
}