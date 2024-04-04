package com.ravspace.composedemo.ui.quote

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<Quote>()

    var isDataLoaded = mutableStateOf(false)
    var currentPage = mutableStateOf(Page.QUOTE_LIST)
    var currentQuote = mutableStateOf(Quote("", ""))

    fun loadAssetFromFiles(context: Context) {
        val inputStream = context.assets.open("quote.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded.value = true
    }

    fun switchPage(quote: Quote) {
        if (currentPage.value == Page.QUOTE_LIST) {
            currentPage.value = Page.QUOTE_DETAIL
            currentQuote.value = quote
        } else {
            currentPage.value = Page.QUOTE_LIST
        }
    }
}

enum class Page {
    QUOTE_LIST,
    QUOTE_DETAIL
}