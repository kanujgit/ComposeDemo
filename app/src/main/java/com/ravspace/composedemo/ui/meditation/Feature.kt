package com.ravspace.composedemo.ui.meditation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    @DrawableRes val imageId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)