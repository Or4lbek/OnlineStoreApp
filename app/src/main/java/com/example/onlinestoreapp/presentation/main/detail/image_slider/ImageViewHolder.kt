package com.example.onlinestoreapp.presentation.main.detail.image_slider

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.databinding.ItemImageSliderBinding

class ImageViewHolder(private val binding: ItemImageSliderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        Glide.with(itemView.context).load(imageUrl).into(binding.itemSliderIv)

    }
}