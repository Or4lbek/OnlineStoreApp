package com.example.onlinestoreapp.presentation.main.feed.flash_sale

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestoreapp.core.R
import com.example.onlinestoreapp.databinding.ItemFlashSaleBinding
import com.example.onlinestoreapp.domain.model.FlashSale

class FlashSaleViewHolder(private val binding: ItemFlashSaleBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(flashSale: FlashSale) {
        binding.apply {
            itemNameTv.text = flashSale.name
            itemCategoryTv.text = flashSale.category
            val flashSalePrice = context.resources.getString(R.string.flash_sale_price)
            itemPriceTv.text = String.format(flashSalePrice, flashSale.price)
            val flashSaleDiscount = context.resources.getString(R.string.flash_sale_discount)
            itemDiscountTv.text = String.format(flashSaleDiscount, flashSale.discount.toString())
        }
        Glide.with(itemView.context).load(flashSale.imageUrl).into(binding.itemProductIv)
    }
}