package com.kdroid.newsapp.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kdroid.newsapp.ui.theme.ComposeDemoTheme

@Composable
fun HomeScreenApp(widthSizeClass: WindowWidthSizeClass) {

    ComposeDemoTheme {

        val navController = rememberNavController()
        val navigationAction = remember(navController) {
            NewsNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route ?: NewsDestinations.HOME_ROUTE

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

        NewsNavGraph()

    }
}

@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}
