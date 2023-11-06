package com.example.custom_slider_ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.custom_slider_ui.model.Item

class ItemDiffUtilCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.title == newItem.title
    }
}