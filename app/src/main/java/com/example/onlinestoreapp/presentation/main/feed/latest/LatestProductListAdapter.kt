package com.example.onlinestoreapp.presentation.main.feed.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.onlinestoreapp.databinding.ItemLatestBinding
import com.example.onlinestoreapp.domain.model.Latest

class LatestProductListAdapter : ListAdapter<Latest, LatestViewHolder>(LatestDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLatestBinding.inflate(layoutInflater, parent, false)
        return LatestViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}