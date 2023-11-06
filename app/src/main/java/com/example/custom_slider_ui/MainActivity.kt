package com.example.custom_slider_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.custom_slider_ui.adapter.ItemAdapter
import com.example.custom_slider_ui.databinding.ActivityMainBinding
import com.example.custom_slider_ui.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            Toast.makeText(this@MainActivity, "Pos ${position}", Toast.LENGTH_LONG).show()
            binding.customRecyclerView.smoothScrollToPosition(position)
        } }
    private val possibleItems = listOf(
        Item("Airplanes", R.drawable.bg_16dp_theme_yellow),
        Item("Cars", R.drawable.bg_16dp_theme_gray),
        Item("Food", R.drawable.bg_16dp_theme_yellow),
        Item("Gas", R.drawable.bg_16dp_theme_gray),
        Item("Home", R.drawable.bg_16dp_theme_yellow)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.customRecyclerView.initialize(itemAdapter)
        binding.customRecyclerView.setViewsToChangeColor(listOf(R.id.list_item_background,R.id.list_item_text))
        itemAdapter.submitList(possibleItems)

    }
}
