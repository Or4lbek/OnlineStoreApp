package com.example.onlinestoreapp.presentation.main.feed.latest

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.core.R
import com.example.onlinestoreapp.databinding.ItemLatestBinding
import com.example.onlinestoreapp.domain.model.Latest

class LatestViewHolder(private val binding: ItemLatestBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(latest: Latest) {
        binding.apply {
            itemNameTv.text = latest.name
            itemCategoryTv.text = latest.category
            val latestPrice = context.resources.getString(R.string.product_price)
            itemPriceTv.text = String.format(latestPrice, latest.price)
        }
        Glide.with(itemView.context).load(latest.imageUrl).into(binding.itemProductIv)
    }
}