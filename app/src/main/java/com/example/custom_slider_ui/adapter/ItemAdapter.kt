package com.example.custom_slider_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.custom_slider_ui.databinding.ItemBinding
import com.example.custom_slider_ui.model.Item

class ItemAdapter(
    val itemClick: (Int, Item) -> Unit,
) : ListAdapter<Item,ItemAdapter.ItemViewHolder>(ItemDiffUtilCallback()) {

    class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.listItemText.text = "${item.title}"
            binding.listItemIcon.setImageResource(item.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
           ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    override fun getItemCount() = currentList.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            itemClick(position,currentList[position])
        }
    }
}