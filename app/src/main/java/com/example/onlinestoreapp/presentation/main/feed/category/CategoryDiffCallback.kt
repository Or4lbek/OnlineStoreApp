package com.example.onlinestoreapp.presentation.main.feed.category

import androidx.recyclerview.widget.DiffUtil
import com.example.onlinestoreapp.domain.model.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
