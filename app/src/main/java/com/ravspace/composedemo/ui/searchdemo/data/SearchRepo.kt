package com.ravspace.composedemo.ui.searchdemo.data

import com.ravspace.composedemo.ui.searchdemo.repository.Repository
import com.ravspace.composedemo.ui.searchdemo.service.ApiService
import javax.inject.Inject

class SearchRepo @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun searchTransactions(term: String) = apiService.searchItems(term)
}
