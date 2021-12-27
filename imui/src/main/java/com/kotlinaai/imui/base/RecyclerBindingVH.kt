package com.kotlinaai.imui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerBindingVH<T: ViewDataBinding>(val binding: T): RecyclerView.ViewHolder(binding.root)