package com.thangadurai.saveotask.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponseItem
import com.thangadurai.saveotask.databinding.LayoutThumbnailBinding
import com.thangadurai.saveotask.extensions.setImage


class ViewPagerAdapter internal constructor(
    val context: Context, val data: MutableList<BasePhotoResponseItem>
) :
    RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: LayoutThumbnailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutThumbnailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        setImage(
            context,
            "${data[position].thumbnailUrl}.jpg",
            holder.binding.ivThumbnail
        )
    }

}
