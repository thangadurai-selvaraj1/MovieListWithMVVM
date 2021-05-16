package com.thangadurai.saveotask.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponseItem
import com.thangadurai.saveotask.databinding.LayoutMovieBinding
import com.thangadurai.saveotask.extensions.setImage
import com.thangadurai.saveotask.utils.RecyclerViewCallBack

class MyAdapter(
    val context: Context,
    val list: ArrayList<BasePhotoResponseItem>,
    var callBack: RecyclerViewCallBack
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: LayoutMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        setImage(
            context,
            "${list[position].thumbnailUrl}.jpg",
            holder.binding.ivMovie
        )
        holder.binding.ivMovie.setOnClickListener {
            callBack.onItemClick(holder.binding.ivMovie, list[position], position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun clearItems() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addItems(lists: ArrayList<BasePhotoResponseItem>) {
        list.addAll(lists)
        notifyDataSetChanged()
    }
}