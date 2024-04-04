package com.ravspace.composedemo.ui.quote

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun QuoteDetails(quote: Quote) {
    BackHandler {
        DataManager.switchPage(Quote("", ""))
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(
                brush =
                Brush.sweepGradient(colors = listOf(Color.LightGray, Color.White))
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(),
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.Center)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp, 24.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.FormatQuote, contentDescription = "Quote Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .rotate(180F)
                )
                Text(
                    text = quote.text,
                    style = MaterialTheme.typography.labelLarge,
                )

                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}