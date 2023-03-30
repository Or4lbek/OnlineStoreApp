package com.example.onlinestoreapp.presentation.main.detail.image_slider

import androidx.recyclerview.widget.DiffUtil

class ImageModelDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}