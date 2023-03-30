package com.example.onlinestoreapp.presentation.main.feed.latest

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.databinding.ItemLatestBinding
import com.example.onlinestoreapp.domain.model.Latest

class LatestViewHolder(private val binding: ItemLatestBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(latest: Latest) {
        binding.apply {
            itemNameTv.text = latest.name
            itemCategoryTv.text = latest.category
            itemPriceTv.text = "$ " + latest.price
        }
        Glide.with(itemView.context).load(latest.image_url).into(binding.itemProductIv)
    }
}