package com.kdroid.newsapp.repository

interface NewsRepo {

    suspend fun getCategory()

    suspend fun getHeadline()
}