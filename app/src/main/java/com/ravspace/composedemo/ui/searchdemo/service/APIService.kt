package com.ravspace.composedemo.ui.searchdemo.service

import com.ravspace.composedemo.ui.searchdemo.data.models.Transaction
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v3/b/6638ada3acd3cb34a843b4a2?meta=false")
    suspend fun searchItems(@Query("term") term: String): List<Transaction>

}