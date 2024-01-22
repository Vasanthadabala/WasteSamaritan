package com.example.wastesamaritan.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wastesamaritan.screens.AboutProjectScreen
import com.example.wastesamaritan.screens.AboutScreen
import com.example.wastesamaritan.screens.ComplaintsScreen
import com.example.wastesamaritan.screens.HomeScreen
import com.example.wastesamaritan.screens.MyRatingsScreen
import com.example.wastesamaritan.screens.ProfileScreen
import com.example.wastesamaritan.screens.SigninScreen
import com.example.wastesamaritan.screens.SyncScreen

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home.route)
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
    }
}