package com.ravspace.composedemo.ui.searchdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravspace.composedemo.ui.searchdemo.data.SearchRepo
import com.ravspace.composedemo.ui.searchdemo.intents.MainIntent
import com.ravspace.composedemo.ui.searchdemo.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {

    private val _intent = MutableSharedFlow<MainIntent>()

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> = _state
    private var job: Job? = null


    init {
        handleIntent()
    }

    private fun handleIntent() {
        job?.cancel()
        job = viewModelScope.launch {
            _intent.collect {
                when (it) {
                    is MainIntent.Search -> {
                        search(it.term)
                    }
                }
            }
        }
    }

    private suspend fun search(query: String) {
        if (query.isBlank()) {
            _state.value = MainState.Idle
            return
        }
        _state.value = MainState.Loading
        try {
            val result = searchRepo.searchTransactions(query)
            _state.value = MainState.Items(result)
        } catch (e: Exception) {
            _state.value = MainState.Error("Error $e")
        }

    }

    fun dispatch(intent: MainIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }

    }
}

