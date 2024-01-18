package com.example.wastesamaritan.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wastesamaritan.R
import com.example.wastesamaritan.ui.theme.MyColor

@Composable
fun DrawerNav(
    selectedItemIndex: Int,
    onItemSelect: (Int) -> Unit,
    onCloseDrawer: () -> Unit,
    navController: NavHostController
) {
    ModalDrawerSheet(
        drawerContainerColor = Color(0XFFF0F5F9)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.title,
                        color = MyColor.text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                },
                selected = selectedItemIndex == index,
                onClick = {
                    onItemSelect(index)
                    onCloseDrawer()
                    navController.navigate(item.title) {
                        popUpTo(item.title) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(5.dp)
                    ) {
                        Image(
                            painterResource(
                                id = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                }
                            ),
                            contentDescription = item.title
                        )
                    }
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = MyColor.background,
                    selectedContainerColor = MyColor.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(60.dp)
            )
        }
    }
}

data class DrawerNavigationItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)

val items = listOf(
    DrawerNavigationItem(
        title = "Home",
        selectedIcon = R.drawable.selectedhome ,
        unselectedIcon = R.drawable.unselectedhome,
    ),
    DrawerNavigationItem(
        title = "About Us",
        selectedIcon = R.drawable.info,
        unselectedIcon = R.drawable.info,
    ),
    DrawerNavigationItem(
        title = "About Project",
        selectedIcon = R.drawable.info,
        unselectedIcon = R.drawable.info,
    ),
    DrawerNavigationItem(
        title = "Logout",
        selectedIcon = R.drawable.logout,
        unselectedIcon = R.drawable.logout,
    )
)