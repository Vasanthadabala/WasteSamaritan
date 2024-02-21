package com.example.wastesamaritan.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.ui.theme.MyColor

@ExperimentalComposeUiApi
@Composable
fun BottomBar(navController: NavHostController) {

    var bottomSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: ""
    bottomSelectedItemIndex = bottomNavItems.indexOfFirst { it.title == currentRoute }

    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        NavigationBar(
            tonalElevation = 5.dp,
            containerColor = MyColor.background
        ) {
            bottomNavItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = bottomSelectedItemIndex == index,
                    onClick = {
                        bottomSelectedItemIndex = index
                        navController.navigate(item.title){
                            popUpTo(item.title){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold ,
                            color = Color(0xff0C2D48))
                    },
                    alwaysShowLabel = true,
                    icon = {
                        Box(
                            modifier = Modifier.size(28.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painterResource(id = if (index == bottomSelectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                }
                                ),
                                contentDescription = item.title
                            )
                        }
                    },
                    colors = if (bottomSelectedItemIndex == index){
                        NavigationBarItemDefaults.colors(
                            indicatorColor = MyColor.secondary
                        )
                    }else{
                        NavigationBarItemDefaults.colors()
                    },
                )
            }
        }
    }
}
data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
)

val bottomNavItems = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = R.drawable.selectedhome,
        unselectedIcon = R.drawable.unselectedhome
    ),
    BottomNavigationItem(
        title = "My Ratings",
        selectedIcon = R.drawable.selectedstar,
        unselectedIcon = R.drawable.unselectedstar
    ),
    BottomNavigationItem(
        title = "Complaints",
        selectedIcon = R.drawable.selectedfeedback,
        unselectedIcon = R.drawable.unselectedfeedback
    ),
    BottomNavigationItem(
        title = "Sync",
        selectedIcon = R.drawable.sync,
        unselectedIcon = R.drawable.sync
    )
)