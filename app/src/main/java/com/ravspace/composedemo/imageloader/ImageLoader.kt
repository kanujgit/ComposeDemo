package com.ravspace.composedemo.imageloader

class ImageLoader(val chache:ImageCache) {
    fun load(url:String):RequestBuilder{
        return RequestBuilder(url,chache)
    }

}