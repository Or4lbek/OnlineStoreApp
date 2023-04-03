package com.example.onlinestoreapp.presentation.main.feed.category

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.databinding.ItemCategoryBinding
import com.example.onlinestoreapp.domain.model.Category

class CategoryViewHolder(private val binding: ItemCategoryBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.itemCategoryNameTv.text = context.resources.getString(category.name)
        binding.itemCategoryIv.setImageResource(category.image)
    }
}
