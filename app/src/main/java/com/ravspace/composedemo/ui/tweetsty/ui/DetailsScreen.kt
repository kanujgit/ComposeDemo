package com.ravspace.composedemo.ui.tweetsty.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ravspace.composedemo.ui.tweetsty.models.TweetListItem
import com.ravspace.composedemo.ui.tweetsty.viewmodels.TweetsViewModel

@Composable
fun DetailsScreen() {
   val viewmodel: TweetsViewModel = hiltViewModel()
    val tweets: State<List<TweetListItem>> = viewmodel.tweets.collectAsState()
    LazyColumn {
        items(tweets.value) {
            TweetListScreen(it)
        }
    }
}

@Composable
fun TweetListScreen(tweetListItem: TweetListItem) {
    Card(
        modifier = Modifier
            .border(2.dp, Color.White, RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = tweetListItem.tweet + " : " + tweetListItem.author,
                modifier = Modifier.padding(10.dp),
                fontSize = 10.sp,
                style = MaterialTheme.typography.labelMedium
            )
        }


    }
}