package com.ravspace.composedemo.ui.quote

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable


@Composable
fun QuoteList(quote: Array<Quote>, onClicked: (quote: Quote) -> Unit) {

    LazyColumn(content = {
        items(quote) {
            QuoteListScreen(quote = it, onClicked = onClicked)
        }
    })
}