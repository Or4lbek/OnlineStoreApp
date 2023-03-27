package com.example.onlinestoreapp.presentation.main.feed.flash_sale

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.databinding.FlashSaleItemBinding

class FlashSaleViewHolder(private val binding: FlashSaleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(flashSale: FlashSale) {
        binding.apply {
            itemNameTv.text = flashSale.name
            itemCategoryTv.text = flashSale.category
            itemPriceTv.text = "$ " + flashSale.price.toString()
            itemDiscountTv.text = flashSale.discount.toString() + "% off"
        }
        Glide.with(itemView.context).load(flashSale.image_url).into(binding.itemProductIv)
    }
}