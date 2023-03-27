package com.example.onlinestoreapp.presentation.main.feed.flash_sale

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.onlinestoreapp.databinding.FlashSaleItemBinding

class FlashSaleProductListAdapter :
    ListAdapter<FlashSale, FlashSaleViewHolder>(FlashSaleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FlashSaleItemBinding.inflate(layoutInflater, parent, false)
        return FlashSaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}