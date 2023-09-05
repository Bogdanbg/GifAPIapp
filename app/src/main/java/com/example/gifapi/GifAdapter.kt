package com.example.gifapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide

class GifAdapter(private val context: Context, private val gifList: List<GifModel>) :
RecyclerView.Adapter<GifAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gif,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gif = gifList[position]

        Glide.with(context)
            .asGif()
            .load(gif.url)
            .into(holder.itemView.gifImageView)
        holder.itemView.gifTitleTextView.text = gif.title
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
