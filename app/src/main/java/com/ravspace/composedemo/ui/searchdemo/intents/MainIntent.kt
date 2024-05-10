package com.ravspace.composedemo.ui.searchdemo.intents


sealed class MainIntent {
    data class Search(val term: String) : MainIntent()
}

