package com.ravspace.composedemo.ui.searchdemo.di

import com.ravspace.composedemo.ui.searchdemo.data.SearchRepo
import com.ravspace.composedemo.ui.searchdemo.repository.Repository
import com.ravspace.composedemo.ui.searchdemo.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun getRepository(apiService: ApiService): Repository {
        return SearchRepo(apiService)
    }
}