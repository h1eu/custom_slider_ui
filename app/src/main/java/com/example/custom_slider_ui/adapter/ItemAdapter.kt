package com.example.custom_slider_ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.custom_slider_ui.databinding.ItemBinding
import com.example.custom_slider_ui.model.Item
import kotlin.math.roundToInt

class ItemAdapter(
    val itemClick: (Int, Item) -> Unit,
) : ListAdapter<Item,ItemViewHolder>(ItemDiffUtilCallback()) {
    private var hasInitParentDimensions = false
    private var maxImgHeight: Int = 0
    private var maxImgWidth: Int = 0
    private var maxIngAspectRatio = 1f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (!hasInitParentDimensions){
            maxImgWidth = parent.width
            maxImgHeight = parent.height
            maxIngAspectRatio = maxImgWidth.toFloat() / maxImgHeight.toFloat()
            hasInitParentDimensions = true
        }

        return ItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun getItemCount() = currentList.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            itemClick(position,currentList[position])
        }
        holder.itemView.layoutParams = RecyclerView.LayoutParams(
            (maxImgWidth * maxIngAspectRatio).roundToInt(),
            RecyclerView.LayoutParams.MATCH_PARENT
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<Item>?) {
        notifyDataSetChanged()
        super.submitList(list)
    }
}