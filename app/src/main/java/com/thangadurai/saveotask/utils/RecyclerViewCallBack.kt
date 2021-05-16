package com.thangadurai.saveotask.utils

import android.view.View

interface RecyclerViewCallBack {
    fun onItemClick(view: View, model: Any, position: Int)
}