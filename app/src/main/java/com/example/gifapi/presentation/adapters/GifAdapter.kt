package com.example.gifapi.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifapi.databinding.ItemGifBinding
import com.example.gifapi.domain.GifModel

class GifAdapter :
RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    private val gifList = mutableListOf<GifModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<GifModel>) {
        gifList.clear()
        gifList.addAll(newData)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: GifModel){
            val splitList = model.url.split("-").last()
            val realLink = "https://media1.giphy.com/media/$splitList/giphy.gif"
            val imageView = binding.gifImageView

            Glide.with(binding.root.context)
                .asGif()
                .fitCenter()
                .load(realLink)
                .into(imageView)

        }
    }
}
