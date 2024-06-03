package com.ravspace.composedemo.ui.searchdemo.data

import com.ravspace.composedemo.ui.searchdemo.di.qualifier.SearchRetrofit
import com.ravspace.composedemo.ui.searchdemo.repository.Repository
import com.ravspace.composedemo.ui.searchdemo.service.ApiService
import javax.inject.Inject
import javax.inject.Named

//class SearchRepo @Inject constructor(@Named("search")private val apiService: ApiService) : Repository {
class SearchRepo @Inject constructor(@SearchRetrofit private val apiService: ApiService) : Repository {
    override suspend fun searchTransactions(term: String) = apiService.searchItems(term)
}
