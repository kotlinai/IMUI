package com.kotlinaai.imui.conversation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinaai.imui.conversation.pojos.UIMessage

abstract class MessageTypeAdapter {

    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, message: UIMessage?)
    abstract fun isType(type: Int): Boolean
    abstract fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder
}