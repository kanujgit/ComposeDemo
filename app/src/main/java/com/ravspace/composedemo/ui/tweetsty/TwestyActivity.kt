package com.ravspace.composedemo.ui.tweetsty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ravspace.composedemo.ui.tweetsty.ui.CategoryScreen
import com.ravspace.composedemo.ui.tweetsty.ui.DetailsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TwestyActivity : ComponentActivity() {
    companion object {
        val TAG = "TwestyApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetToolbar()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SetToolbar() {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = "Twesty")
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black, titleContentColor = Color.White
                )
            )
        }) {
            Box(modifier = Modifier.padding(it)) {
                App()
            }
        }
    }

    @Composable
    private fun App() {
        val navAppController = rememberNavController()
        NavHost(
            navController = navAppController, startDestination = "category"
        ) {
            composable(route = "category") {
                CategoryScreen {
                    navAppController.navigate("detail/$it")
                }
            }

            composable(
                route = "detail/{category}",
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) {
                DetailsScreen()
            }
        }
    }
}