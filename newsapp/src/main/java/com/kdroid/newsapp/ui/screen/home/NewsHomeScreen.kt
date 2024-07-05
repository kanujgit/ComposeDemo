package com.kdroid.newsapp.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.kdroid.newsapp.ui.screen.TopAppBar
import com.kdroid.newsapp.ui.viewmodels.HomeViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHomeScreen(
    homeViewModel: HomeViewmodel,
) {

    //  val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val homeListLazyListState = rememberLazyListState()
    Scaffold(topBar = {
        TopAppBar("App", false) { }
    }) { paddingValues ->
        CategoryUi(paddingValues)
    }

    //category ui

}

@Composable
fun CategoryUi(paddingValues: PaddingValues) {

}
