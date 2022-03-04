package com.android.getapi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageLoader {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImage(view : ImageView, imageUrl : String) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }


}