package com.ravspace.composedemo.ui.searchdemo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ravspace.composedemo.ui.searchdemo.data.models.Transaction
import com.ravspace.composedemo.ui.searchdemo.intents.MainIntent
import com.ravspace.composedemo.ui.searchdemo.state.MainState
import com.ravspace.composedemo.ui.searchdemo.viewmodel.SearchViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(searchViewModel: SearchViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val state =
        searchViewModel.state.collectAsStateWithLifecycle(lifecycle, Lifecycle.State.RESUMED).value

    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)

    ) {
        OutlinedTextField(value = searchText.value, onValueChange = { it ->
            searchText.value = it
            searchViewModel.dispatch(MainIntent.Search(it))
        })

        when (state) {

            is MainState.Idle -> {
                Text(text = "Idle")
            }

            is MainState.Loading -> {
                Text(
                    text = "Loading",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(10.dp)
                )
            }

            is MainState.Items -> {
                SearchItemScreen(state.transactions)
            }

            is MainState.Error -> {
                Text(text = "Error")
            }

            else -> {
                Text(text = "Unknown")
            }
        }
    }
}

@Composable
fun SearchItemScreen(items: List<Transaction>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        LazyColumn {
            items(items) {
                Text(
                    text = it.amount + " " + it.merchant,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

