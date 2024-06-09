package com.ravspace.composedemo.imageloader

import android.widget.ImageView
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

/*
ImageLoader - >Loading images from the internet

RequestBuilder -> is responsible for building the request(shape, callbase)

ImageFetcher ->Api Call from network

ImageCache -> making cache( hashmap)

Shape -> Passing shape object


 */

fun main() = runBlocking{


    val imageLoader = ImageLoader(ImageCache())
    imageLoader.load("https://image.com")
        .shape(CircleShape())
       // .into(ImageView(this))
        .onFail {
            //
        }.onSuccess {
            //
        }
        .execute()





}
