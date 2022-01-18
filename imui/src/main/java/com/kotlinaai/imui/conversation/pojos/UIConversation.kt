package com.kotlinaai.imui.conversation.pojos

data class UIConversation(
    val id: String,
    val avatar: String,
    val name: String,
    val message: String? = null,
    val timeString: String? = null,
    val hasUnReading: Boolean = false,
    val ext: Any? = null)
