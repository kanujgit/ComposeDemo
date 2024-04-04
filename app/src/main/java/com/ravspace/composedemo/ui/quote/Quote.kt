package com.ravspace.composedemo.ui.quote

data class Quote(val text: String, val author: String){
    override fun toString(): String {
        return "Quote(text='$text', author='$author')"
    }
}