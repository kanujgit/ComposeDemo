package com.kdroid.newsapp.ui

import androidx.navigation.NavHostController

object NewsDestinations {
    const val HOME_ROUTE = "home"
    const val INTERESTS_ROUTE = "interests"
}

class NewsNavigationActions(navController: NavHostController) {

    val navigateToHome: () -> Unit = {
        navController.navigate(NewsDestinations.HOME_ROUTE)

//        popUpTo(navController.graph.findStartDestination().id) {
//            saveState = true
//        }
//        // Avoid multiple copies of the same destination when
//        // reselecting the same item
//        launchSingleTop = true
//        // Restore state when reselecting a previously selected item
//        restoreState = true

    }

    val navigateToInterests: () -> Unit = {
        navController.navigate(NewsDestinations.INTERESTS_ROUTE)
    }

}