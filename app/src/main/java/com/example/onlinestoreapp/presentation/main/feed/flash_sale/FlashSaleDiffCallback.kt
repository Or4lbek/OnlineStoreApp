package com.example.onlinestoreapp.presentation.main.feed.flash_sale

import androidx.recyclerview.widget.DiffUtil
import com.example.onlinestoreapp.domain.model.FlashSale

class FlashSaleDiffCallback : DiffUtil.ItemCallback<FlashSale>() {
    override fun areItemsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
        return oldItem == newItem
    }
}