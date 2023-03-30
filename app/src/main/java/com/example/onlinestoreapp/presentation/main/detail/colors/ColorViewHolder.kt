package com.example.onlinestoreapp.presentation.main.detail.colors

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.databinding.ItemColorBinding

class ColorViewHolder(private val binding: ItemColorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(color: String) {
        val colorInt = Color.parseColor(color)
        binding.itemColorCv.setBackgroundColor(colorInt)
    }
}
