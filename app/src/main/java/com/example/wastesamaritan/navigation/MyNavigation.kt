package com.example.wastesamaritan.navigation

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
import com.example.wastesamaritan.screens.individual_house.individual.IndividualHouseViewModel
import com.example.wastesamaritan.screens.AboutProjectScreen
import com.example.wastesamaritan.screens.AboutScreen
import com.example.wastesamaritan.screens.ComplaintsScreen
import com.example.wastesamaritan.screens.DataScreen
import com.example.wastesamaritan.screens.home_screen.HomeScreen
import com.example.wastesamaritan.screens.MyRatingsScreen
import com.example.wastesamaritan.screens.ProfileScreen
import com.example.wastesamaritan.screens.SigninScreen
import com.example.wastesamaritan.screens.individual_house.individual.IndividualHouseScreen
import com.example.wastesamaritan.screens.individual_house.notsegregated.NotSegregatedScreen
import com.example.wastesamaritan.screens.individual_house.notsegregated.NotSegregatedViewModel
import com.example.wastesamaritan.screens.individual_house.segregated.SegregatedScreen
import com.example.wastesamaritan.screens.individual_house.segregated.SegregatedViewModel

@ExperimentalGlideComposeApi
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun MyNavigation(
    context:Context,
    individualHouseViewModel: IndividualHouseViewModel,
    notSegregatedViewModel: NotSegregatedViewModel,
    segregatedViewModel:SegregatedViewModel
){
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
            SegregatedScreen(navController,segregatedViewModel, id)
        }
        composable(
            "${NotSegregated.route}/{${NotSegregated.itemID}}",
            arguments = listOf(navArgument(NotSegregated.itemID) { type = NavType.StringType })
        ){
            val id = requireNotNull(it.arguments?.getString(NotSegregated.itemID))
            NotSegregatedScreen(navController,notSegregatedViewModel,id)
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