package com.ravspace.composedemo.imageloader

import android.graphics.Bitmap

class ImageCache(private var cahce: MutableMap<String, Bitmap> = mutableMapOf()) {
    fun get(url: String) = cahce[url]

    fun put(url: String, bitmap: Bitmap) {
        cahce[url] = bitmap
    }
}