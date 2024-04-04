package com.ravspace.composedemo.ui.meditation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravspace.composedemo.R
import com.ravspace.composedemo.ui.theme.AquaBlue
import com.ravspace.composedemo.ui.theme.Beige1
import com.ravspace.composedemo.ui.theme.Beige2
import com.ravspace.composedemo.ui.theme.Beige3
import com.ravspace.composedemo.ui.theme.BlueViolet1
import com.ravspace.composedemo.ui.theme.BlueViolet2
import com.ravspace.composedemo.ui.theme.BlueViolet3
import com.ravspace.composedemo.ui.theme.ButtonBlue
import com.ravspace.composedemo.ui.theme.DarkerButtonBlue
import com.ravspace.composedemo.ui.theme.DeepBlue
import com.ravspace.composedemo.ui.theme.LightGreen1
import com.ravspace.composedemo.ui.theme.LightGreen2
import com.ravspace.composedemo.ui.theme.LightGreen3
import com.ravspace.composedemo.ui.theme.LightRed
import com.ravspace.composedemo.ui.theme.OrangeYellow1
import com.ravspace.composedemo.ui.theme.OrangeYellow2
import com.ravspace.composedemo.ui.theme.OrangeYellow3
import com.ravspace.composedemo.ui.theme.TextWhite
import com.ravspace.composedemo.ui.theme.Typography

class MediationUI {

    @Composable
    fun HomeScreen() {
        Box(
            modifier = Modifier
                .background(DeepBlue)
                .fillMaxSize()
        ) {
            Column {
                GreetingName()
                ChipSection(listOf("Sweet Sleep", "Insomnia", "Depression", "Anxiety"))
                CurrentMeditation()

                FeatureSection(
                    listOf(
                        Feature(
                            "Sleep Stories",
                            R.drawable.ic_headphone,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3,
                        ), Feature(
                            "Tips for sleep",
                            R.drawable.ic_videocam,
                            LightGreen1,
                            LightGreen2,
                            LightGreen3,
                        ), Feature(
                            "Rise early",
                            R.drawable.ic_headphone,
                            OrangeYellow1,
                            OrangeYellow2,
                            OrangeYellow3,
                        ),

                        Feature(
                            "Clarity of mind",
                            R.drawable.ic_headphone,
                            Beige1,
                            Beige2,
                            Beige3,
                        ), Feature(
                            "Clarity of mind",
                            R.drawable.ic_headphone,
                            Beige1,
                            Beige2,
                            Beige3,
                        )
                    )
                )
                BottomMenu(
                    items = listOf(
                        BottomMenuContent("Home", R.drawable.ic_home),
                        BottomMenuContent("Meditate", R.drawable.ic_bubble),
                        BottomMenuContent("Sleep", R.drawable.ic_moon),
                        BottomMenuContent("Music", R.drawable.ic_music),
                        BottomMenuContent("Profile", R.drawable.ic_profile),
                    )
                )

            }
        }
    }

        @Composable
        fun BottomMenu(
            items: List<BottomMenuContent>,
            modifier: Modifier = Modifier,
            activeHighlightColor: Color = ButtonBlue,
            activeTextColor: Color = Color.White,
            inactiveTextColor: Color = AquaBlue,
            initialSelectedItemIndex: Int = 0
        ) {
            var selectedItemIndex by remember {
                mutableStateOf(initialSelectedItemIndex)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .background(DeepBlue)
                    .padding(15.dp)
            ) {
                items.forEachIndexed { index, item ->
                    BottomMenuItem(
                        item = item,
                        isSelected = index == selectedItemIndex,
                        activeHighlightColor = activeHighlightColor,
                        activeTextColor = activeTextColor,
                        inactiveTextColor = inactiveTextColor
                    ) {
                        selectedItemIndex = index
                    }
                }
            }
        }

        @Composable
        fun BottomMenuItem(
            item: BottomMenuContent,
            isSelected: Boolean = false,
            activeHighlightColor: Color = ButtonBlue,
            activeTextColor: Color = Color.White,
            inactiveTextColor: Color = AquaBlue,
            onItemClick: () -> Unit
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.clickable {
                    onItemClick()
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) activeHighlightColor else Color.Transparent)
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = item.imageId),
                        contentDescription = item.content,
                        tint = if (isSelected) activeTextColor else inactiveTextColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = item.content,
                    color = if(isSelected) activeTextColor else inactiveTextColor
                )
            }
        }

//                BottomMenu(items = listOf(
//                    BottomMenuContent("Home", R.drawable.ic_home),
//                    BottomMenuContent("Meditate", R.drawable.ic_bubble),
//                    BottomMenuContent("Sleep", R.drawable.ic_moon),
//                    BottomMenuContent("Music", R.drawable.ic_music),
//                    BottomMenuContent("Profile", R.drawable.ic_profile),
//                ), modifier = Modifier.align(Alignment.End))
//            }
//        }
//    }
//
//    @Composable
//    fun BottomMenu(
//        items: List<BottomMenuContent>,
//        modifier: Modifier = Modifier,
//        activeHighlightColor: Color = ButtonBlue,
//        activeTextColor: Color = Color.White,
//        inactiveTextColor: Color = AquaBlue,
//        initialSelectedItemIndex: Int = 0
//    ) {
//
//        var  selectedItemIndex by remember {
//            mutableIntStateOf(initialSelectedItemIndex)
//        }
//        Row(
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//                .fillMaxWidth()
//                .background(DeepBlue)
//                .padding(15.dp),
//        ) {
//            items.forEachIndexed { index, item ->
//                BottomMenuItem(
//                    item, isSelected = index ==  selectedItemIndex,
//                    activeHighlightColor = activeHighlightColor,
//                    activeTextColor= activeTextColor,
//                    inactiveTextColor = inactiveTextColor
//                ) {
//                    selectedItemIndex = index
//                }
//            }
//        }
//    }
//
//    @Composable
//    fun BottomMenuItem(
//        item: BottomMenuContent,
//        isSelected: Boolean = false,
//        activeHighlightColor: Color = ButtonBlue,
//        activeTextColor: Color = Color.White,
//        inactiveTextColor: Color = AquaBlue,
//        onItemClick: () -> Unit
//    ) {
//
//        Column(horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier.clickable {
//                onItemClick()
//            }) {
//
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(if (isSelected) activeHighlightColor else Color.Transparent)
//                    .padding(18.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = item.imageId),
//                    contentDescription = item.content,
//                    tint = if (isSelected) activeTextColor else inactiveTextColor,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//
//            Text(
//                text = item.content,
//                color = if (isSelected) activeTextColor else inactiveTextColor,
//                style = Typography.labelSmall
//            )
//        }
//    }


    @Composable
    fun GreetingName(name: String = "Anuj") {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {

            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Hello $name", style = Typography.labelLarge
                )
                Text(
                    text = "We wish you a great day ahead", style = Typography.labelSmall
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }

    @Composable
    fun ChipSection(chips: List<String>) {
        var selectionChipIndex by remember {
            mutableIntStateOf(0)
        }

        LazyRow {
            items(chips.size) {
                Box(modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable { selectionChipIndex = it }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectionChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)) {
                    Text(
                        text = chips[it], color = TextWhite, style = Typography.labelSmall

                    )
                }
            }
        }
    }

    @Composable
    fun CurrentMeditation(color: Color = LightRed) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Daily Thought", style = Typography.labelMedium
                )
                Text(
                    text = "Meditation - 3-10 min", style = Typography.labelSmall, color = TextWhite
                )
            }

            Box(modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .clickable { }
                .padding(10.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.Center),
                )

            }

        }

    }

    @Composable
    fun FeatureSection(feature: List<Feature>) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Features", style = Typography.labelLarge, modifier = Modifier.padding(15.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(feature.size) {
                    FeatureCard(feature[it])
                }

            }

        }
    }

    @Composable
    private fun FeatureCard(feature: Feature) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(7.5.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(feature.darkColor)

        ) {
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            // Medium color path
            val mediumColorPath1 = Offset(0f, height * 03f)
            val mediumColorPath2 = Offset(width * 0.1f, height * 0.35f)
            val mediumColorPath3 = Offset(width * 0.4f, height * 0.05f)
            val mediumColorPath4 = Offset(width * 0.75f, height * 0.7f)
            val mediumColorPath5 = Offset(width * 1.4f, -height.toFloat())

            val mediumColorPath = Path().apply {
                moveTo(mediumColorPath1.x, mediumColorPath1.y)
                standardQuadTo(mediumColorPath1, mediumColorPath2)
                standardQuadTo(mediumColorPath2, mediumColorPath3)
                standardQuadTo(mediumColorPath3, mediumColorPath4)
                standardQuadTo(mediumColorPath4, mediumColorPath5)

                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }


            // light color path
            val lightColorPath1 = Offset(0f, height * 0.35f)
            val lightColorPath2 = Offset(width * 0.1f, height * 0.4f)
            val lightColorPath3 = Offset(width * 0.3f, height * 0.35f)
            val lightColorPath4 = Offset(width * 0.65f, height.toFloat())
            val lightColorPath5 = Offset(width * 1.4f, -height.toFloat() / 3f)

            val lightColorPath = Path().apply {
                moveTo(lightColorPath1.x, lightColorPath1.y)
                standardQuadTo(lightColorPath1, lightColorPath2)
                standardQuadTo(lightColorPath2, lightColorPath3)
                standardQuadTo(lightColorPath3, lightColorPath4)
                standardQuadTo(lightColorPath4, lightColorPath5)

                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }

            Canvas(modifier = Modifier.fillMaxSize()) {
                drawPath(
                    path = mediumColorPath, color = feature.mediumColor
                )

                drawPath(
                    path = lightColorPath, color = feature.lightColor
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = feature.title,
                    style = Typography.labelMedium,
                    lineHeight = 26.sp,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Icon(
                    painter = painterResource(id = feature.imageId),
                    contentDescription = feature.title,
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.BottomStart)
                )

                Text(text = "Start",
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clickable { }
                        .clip(RoundedCornerShape(10.dp))
                        .background(ButtonBlue)
                        .padding(vertical = 6.dp, horizontal = 15.dp))
            }
        }
    }
}