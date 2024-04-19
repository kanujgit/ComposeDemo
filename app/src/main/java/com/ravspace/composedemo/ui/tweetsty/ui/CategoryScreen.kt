package com.ravspace.composedemo.ui.tweetsty.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ravspace.composedemo.R
import com.ravspace.composedemo.ui.tweetsty.viewmodels.CategoryViewModel


@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {

    val viewmodel: CategoryViewModel = hiltViewModel()
    val category: State<List<String>> = viewmodel.category.collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(category.value.distinct()) { item ->
            CategoryItem(item, onClick)
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .clickable { onClick(category) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(5.dp),

        ) {

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .border(5.dp, Color.White)
                .paint(
                    painter = painterResource(id = R.drawable.wave_haikei),
                    contentScale = ContentScale.FillBounds
                ),

            contentAlignment = Alignment.BottomCenter

        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
