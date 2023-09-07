package com.example.gifapi.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifapi.databinding.ItemGifBinding
import com.example.gifapi.domain.GifModel

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
        val imageView = holder.binding.gifImageView

        Glide.with(context)
            .asGif()
            .load(gif.url)
            .into(imageView)
    }

    fun updateData(newData: List<GifModel>?) {
        gifList.clear()
        if (newData != null){
            gifList.addAll(newData)
        }
    }

    inner class ViewHolder(val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root)
}
