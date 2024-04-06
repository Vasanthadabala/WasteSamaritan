package com.example.wastesamaritan.navigation

import com.example.wastesamaritan.data.viewmodel.SegregatedViewModel
import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.data.viewmodel.IndividualHouseViewModel
import com.example.wastesamaritan.data.viewmodel.NotSegregatedViewModel
import com.example.wastesamaritan.data.viewmodel.RoomDatabaseViewModel
import com.example.wastesamaritan.screens.AboutProjectScreen
import com.example.wastesamaritan.screens.AboutScreen
import com.example.wastesamaritan.screens.ComplaintsScreen
import com.example.wastesamaritan.screens.DataScreen
import com.example.wastesamaritan.screens.HomeScreen
import com.example.wastesamaritan.screens.MyRatingsScreen
import com.example.wastesamaritan.screens.ProfileScreen
import com.example.wastesamaritan.screens.SigninScreen
import com.example.wastesamaritan.screens.SyncScreen
import com.example.wastesamaritan.screens.individualscreen.IndividualHouseScreen
import com.example.wastesamaritan.screens.individualscreen.NotSegregatedScreen
import com.example.wastesamaritan.screens.individualscreen.SegregatedScreen

@ExperimentalGlideComposeApi
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun MyNavigation(context:Context, individualHouseViewModel: IndividualHouseViewModel ){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = destination(context) )//destination(context)
    {
        composable(Signin.route){
            SigninScreen(navController)
        }
        composable(Home.route){
            HomeScreen(navController,individualHouseViewModel)
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
//        composable(Sync.route){
//            SyncScreen(navController)
//        }
        composable(IndividualHouse.route){
            IndividualHouseScreen(navController,individualHouseViewModel)
        }
        composable(
            "${Segregated.route}/{${Segregated.itemID}}",
            arguments = listOf(navArgument(Segregated.itemID) { type = NavType.StringType })
        ){
            val id = requireNotNull(it.arguments?.getString(Segregated.itemID))
            SegregatedScreen(navController,id)
        }
        composable(
            "${NotSegregated.route}/{${NotSegregated.itemID}}",
            arguments = listOf(navArgument(NotSegregated.itemID) { type = NavType.StringType })
        ){
            val id = requireNotNull(it.arguments?.getString(NotSegregated.itemID))
            NotSegregatedScreen(navController, id)
        }
        composable(Data.route){
            DataScreen(navController)
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