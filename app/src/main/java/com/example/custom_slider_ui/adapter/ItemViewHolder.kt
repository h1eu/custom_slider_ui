package com.example.custom_slider_ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.custom_slider_ui.databinding.ItemBinding
import com.example.custom_slider_ui.model.Item

class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {

//        binding.listItemText.text = "${item.title}"
        binding.layout.setBackgroundResource(item.icon)
    }
}