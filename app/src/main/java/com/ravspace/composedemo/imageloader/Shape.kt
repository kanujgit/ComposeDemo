package com.ravspace.composedemo.imageloader

import android.graphics.Bitmap


interface Shape{
    fun shape(bitmap: Bitmap):Bitmap
}
class CircleShape : Shape {
    override fun shape(bitmap: Bitmap):Bitmap {
        return bitmap
    }

}