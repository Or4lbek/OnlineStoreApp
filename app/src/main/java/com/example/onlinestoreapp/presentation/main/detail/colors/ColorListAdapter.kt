package com.example.onlinestoreapp.presentation.main.detail.colors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.onlinestoreapp.databinding.ItemColorBinding


class ColorListAdapter :
    ListAdapter<String, ColorViewHolder>(ColorDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(layoutInflater, parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}