package com.ravspace.composedemo.ui.quote

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ravspace.composedemo.MainActivity.Companion.TAG


@Composable
fun LaunchQuoteApp() {
    if (DataManager.isDataLoaded.value) {
        if (DataManager.currentPage.value == Page.QUOTE_LIST) {
            QuoteListScreen(data = DataManager.data) {
                DataManager.switchPage(quote = it)
                DataManager.currentQuote.value = it

            }
        } else {
            DataManager.currentQuote.value.let {
                QuoteDetails(quote = it)
            }
        }
    }

}


@Composable
fun QuoteListScreen(data: Array<Quote>, onClicked: (quote: Quote) -> Unit) {
    Log.d(TAG, "QuoteListScreen: $data")
    Column {
        Text(
            text = "Quotes App",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(8.dp, 24.dp)
                .fillMaxWidth(1f)
        )

        QuoteList(quote = data, onClicked)
    }
}