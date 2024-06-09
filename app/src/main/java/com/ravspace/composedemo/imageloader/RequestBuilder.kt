package com.ravspace.composedemo.imageloader

import android.widget.ImageView

class RequestBuilder(
    private val url: String, private val cahce: ImageCache,
    private var target: ImageView ?= null ,
    private var shape: Shape? = null,
    private var successCallback:(() -> Unit)? =null,
    private var failCallback:(() -> Unit)? =null
) {
    fun shape(shape: Shape):RequestBuilder{
        this.shape =shape
        return this
    }

    fun into(target: ImageView):RequestBuilder{
        this.target =target
        return this
    }

    fun onSuccess(callback: () -> Unit):RequestBuilder{
        this.successCallback = callback
        return this
    }

    fun onFail(callback: () -> Unit):RequestBuilder{
        this.failCallback = callback
        return this
    }

    fun execute(){
        val chacheBitmap = cahce.get(url)
        if(chacheBitmap!=null){
            target?.setImageBitmap(chacheBitmap)
        }else{
         ImageFetcher(shape,url,successCallback,failCallback,cahce,target).getImageBitmap()
        }

    }
}