package com.ravspace.composedemo.ui.searchdemo.state

import com.ravspace.composedemo.ui.searchdemo.data.models.Transaction

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Items(val transactions: List<Transaction>) : MainState()
    data class Error(val error: String) : MainState()
}