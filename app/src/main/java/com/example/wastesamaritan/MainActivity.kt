package com.example.wastesamaritan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.wastesamaritan.screens.individualHouse.individual.IndividualHouseViewModel
import com.example.wastesamaritan.navigation.MyNavigation
import com.example.wastesamaritan.screens.individualHouse.notsegregated.NotSegregatedViewModel
import com.example.wastesamaritan.screens.individualHouse.segregated.SegregatedViewModel
import com.example.wastesamaritan.ui.theme.WasteSamaritanTheme

@ExperimentalGlideComposeApi
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    private val individualHouseViewModel: IndividualHouseViewModel by viewModels()
    private val notSegregatedViewModel: NotSegregatedViewModel by viewModels()
    private val segregatedViewModel: SegregatedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WasteSamaritanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavigation(
                        context = applicationContext,
                        individualHouseViewModel,
                        notSegregatedViewModel,
                        segregatedViewModel
                    )
                }
            }
        }
    }
}
