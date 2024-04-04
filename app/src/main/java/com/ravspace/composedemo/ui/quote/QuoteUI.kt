package com.ravspace.composedemo.ui.quote

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuoteListScreen(quote: Quote, onClicked: (quote: Quote) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
            .clickable { onClicked(quote) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            QuoteImage()
            QuoteContent(quote)
        }
    }
}

@Composable
fun QuoteContent(quote: Quote, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = quote.text,
            style = MaterialTheme.typography.labelLarge,
        )

        Box(
            modifier = modifier
                .padding(top = 15.dp, bottom = 2.dp)
                .fillMaxWidth(.4f)
                .height(1.dp)
                .background(Color.LightGray)
        )
        Text(
            text = quote.author,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Thin,
            modifier = modifier.padding(top = 4.dp)
        )

    }
}

@Composable
fun QuoteImage(modifier: Modifier = Modifier) {
    Image(
        imageVector = Icons.Filled.FormatQuote,
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Quote Image",
        alignment = Alignment.TopStart,
        modifier = modifier
            .size(40.dp)
            .background(Color.Black)
            .rotate(180f),
    )

    Spacer(modifier = modifier.size(16.dp))
}
