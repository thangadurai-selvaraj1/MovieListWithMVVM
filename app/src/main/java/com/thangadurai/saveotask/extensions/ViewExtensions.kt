package com.thangadurai.saveotask.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.thangadurai.saveotask.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun setImage(context: Context, url: String, view: ImageView) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_loading)
        .into(view)
}
