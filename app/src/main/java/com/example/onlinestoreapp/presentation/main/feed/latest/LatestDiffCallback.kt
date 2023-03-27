package com.example.onlinestoreapp.presentation.main.feed.latest

import androidx.recyclerview.widget.DiffUtil

class LatestDiffCallback : DiffUtil.ItemCallback<Latest>() {
    override fun areItemsTheSame(oldItem: Latest, newItem: Latest): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Latest, newItem: Latest): Boolean {
        return oldItem == newItem
    }
}