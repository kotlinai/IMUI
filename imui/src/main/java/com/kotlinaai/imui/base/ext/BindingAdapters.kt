package com.kotlinaai.imui.base.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imagePath"])
fun setImage(imageView: ImageView, path: String) {
    Glide.with(imageView)
        .load(path)
        .into(imageView)
}