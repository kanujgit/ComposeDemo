package com.ravspace.composedemo.ui.searchdemo.repository

import com.ravspace.composedemo.ui.searchdemo.data.models.Transaction

interface Repository {
    suspend fun searchTransactions(term: String) : List<Transaction>
}