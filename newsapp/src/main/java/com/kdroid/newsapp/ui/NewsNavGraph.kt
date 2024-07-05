package com.kdroid.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdroid.newsapp.ui.screen.home.NewsHomeScreen
import com.kdroid.newsapp.ui.viewmodels.HomeViewmodel


@Composable
fun NewsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NewsDestinations.HOME_ROUTE,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(route = NewsDestinations.HOME_ROUTE) { navBackStackEntry ->
            val homeViewModel:HomeViewmodel = hiltViewModel()
            NewsHomeScreen(homeViewModel)
        }

        composable(route = NewsDestinations.INTERESTS_ROUTE) { navBackStackEntry ->

        }

    }

}
