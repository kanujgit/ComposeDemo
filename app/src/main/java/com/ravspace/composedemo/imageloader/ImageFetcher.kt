package com.ravspace.composedemo.imageloader

import android.graphics.Bitmap
import android.widget.ImageView

//responsible for making api call
class ImageFetcher(val shape: Shape?,
                   val url: String,
                   val successCallback: (() -> Unit)?,
                   val failCallback: (() -> Unit)?,
                   val cache: ImageCache,
                    val target: ImageView?) {

    fun getImageBitmap()  {
        val bitmap:Bitmap = downloadImage(url)

        if(bitmap!=null){
            cache.put(url,bitmap)
            shape?.shape(bitmap)
            target?.setImageBitmap(bitmap)
            successCallback?.invoke()
        }else{
            failCallback?.invoke()
        }
    }


    fun downloadImage(url:String): Bitmap = null as Bitmap
}