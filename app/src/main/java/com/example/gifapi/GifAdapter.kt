package com.example.gifapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.gifapi.databinding.ItemGifBinding

class GifAdapter(private val context: Context, private val gifList: MutableList<GifModel>) :
RecyclerView.Adapter<GifAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gif = gifList[position]

        holder.binding.gifTitleTextView.text = gif.title

        Glide.with(context)
            .asGif()
            .load(gif.url)
            .into(holder.binding.gifImageView)
    }

    fun updateData(newData: List<GifModel>?) {
        gifList.clear()
        if (newData != null){
            gifList.addAll(newData)
        }
    }

    inner class ViewHolder(val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root)
}
