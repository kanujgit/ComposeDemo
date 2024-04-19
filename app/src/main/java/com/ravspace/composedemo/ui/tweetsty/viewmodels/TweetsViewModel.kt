package com.ravspace.composedemo.ui.tweetsty.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravspace.composedemo.ui.tweetsty.models.TweetListItem
import com.ravspace.composedemo.ui.tweetsty.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle["category"] ?: "Development"
            repository.getTweets(category)
        }
    }
}