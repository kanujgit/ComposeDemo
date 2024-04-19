package com.ravspace.composedemo.ui.tweetsty.repository

import com.ravspace.composedemo.ui.tweetsty.api.TweetApi
import com.ravspace.composedemo.ui.tweetsty.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(val tweetApi: TweetApi) {

    private val _category = MutableStateFlow<List<String>>(emptyList())
    val category: StateFlow<List<String>>
        get() = _category

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets


    suspend fun getCategory() {
        val response = tweetApi.getCategory()
        if (response.isSuccessful && response.body() != null) {
            _category.emit(response.body() ?: emptyList())
        } else {
            throw IllegalArgumentException("Error fetching category")
        }

    }

    suspend fun getTweets(category: String) {
        val response = tweetApi.getTweet("tweet[?(@.category ==  \"$category\")]")
        if (response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body() ?: emptyList())
        } else {
            throw IllegalArgumentException("Error fetching category")
        }

    }
}