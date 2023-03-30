package com.example.onlinestoreapp.presentation.main.feed.category

import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.databinding.ItemCategoryBinding
import com.example.onlinestoreapp.domain.model.Category

class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.itemCategoryNameTv.text = category.name
        binding.itemCategoryIv.setImageResource(category.image)
    }
}
