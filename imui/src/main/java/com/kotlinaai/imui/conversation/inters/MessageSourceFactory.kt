package com.kotlinaai.imui.conversation.inters

fun interface MessageSourceFactory<KEY: Any> {
    fun getMessageSource(): MessageSource<KEY>
}