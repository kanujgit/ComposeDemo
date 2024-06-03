package com.ravspace.composedemo.ui.searchdemo.di

import com.ravspace.composedemo.ui.searchdemo.di.qualifier.SearchRetrofit
import com.ravspace.composedemo.ui.searchdemo.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkSearchModule {

    @Provides
    @Singleton
    // @Named("search")
    @SearchRetrofit
    fun provideRetrofitSearch(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @SearchRetrofit
    //@Named("search")
    fun provideSearchApi(retrofitApi: Retrofit): ApiService {
        return retrofitApi.create(ApiService::class.java)
    }
}