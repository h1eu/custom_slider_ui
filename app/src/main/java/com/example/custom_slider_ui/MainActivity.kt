package com.example.custom_slider_ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.custom_slider_ui.adapter.ItemAdapter
import com.example.custom_slider_ui.databinding.ActivityMainBinding
import com.example.custom_slider_ui.decoration.BoundOffsetDecoration
import com.example.custom_slider_ui.decoration.LinearDecoration
import com.example.custom_slider_ui.layoutmanager.HorizontalLayoutManager
import com.example.custom_slider_ui.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var snapHelper: SnapHelper
    private val linearLayoutManager =
        HorizontalLayoutManager(this)
    private var oldPos = -1
    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
        }
    }

    private fun scrollLeftWithOffsetToPos(position: Int) {
        linearLayoutManager.scrollToPosition(position)
        binding.rv.doOnPreDraw {
            val targetView = linearLayoutManager.findViewByPosition(position) ?: return@doOnPreDraw
            val distanceToFinalSnap =
                snapHelper.calculateDistanceToFinalSnap(linearLayoutManager, targetView)
                    ?: return@doOnPreDraw
            linearLayoutManager.scrollToPositionWithOffset(position, -distanceToFinalSnap.first())
        }
    }

    private fun scrollRightWithOffsetToPos(position: Int) {
        linearLayoutManager.scrollToPosition(position)
        binding.rv.doOnPreDraw {
            val targetView = linearLayoutManager.findViewByPosition(position) ?: return@doOnPreDraw
            val distanceToFinalSnap =
                snapHelper.calculateDistanceToFinalSnap(linearLayoutManager, targetView)
                    ?: return@doOnPreDraw
            linearLayoutManager.scrollToPositionWithOffset(position, distanceToFinalSnap.first())
        }
    }

    private val possibleItems = listOf(
        Item("Airplanes", R.drawable.img_theme_test),
        Item("Cars", R.drawable.img_theme_test),
        Item("Food", R.drawable.img_theme_test),
        Item("Gas", R.drawable.img_theme_test),
        Item("Home", R.drawable.img_theme_test),
        Item("Airplanes", R.drawable.img_theme_test),
        Item("Cars", R.drawable.img_theme_test),
        Item("Food", R.drawable.img_theme_test),
        Item("Gas", R.drawable.img_theme_test),
        Item("Home", R.drawable.img_theme_test)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        snapHelper = PagerSnapHelper()
        binding.rv.apply {
            layoutManager = linearLayoutManager
            adapter = itemAdapter
            itemAdapter.submitList(possibleItems)
            scrollLeftWithOffsetToPos(1)
            addItemDecoration(LinearDecoration(resources.getDimensionPixelOffset(R.dimen.spacing)))
            addItemDecoration(BoundOffsetDecoration())
            snapHelper.attachToRecyclerView(binding.rv)
        }

//        binding.carouselRecyclerview.adapter = itemAdapter
//        itemAdapter.submitList(possibleItems)
//        binding.carouselRecyclerview.apply {
//            set3DItem(true)
//            setAlpha(true)
//            setInfinite(true)
//        }
//        snapHelper.attachToRecyclerView(binding.carouselRecyclerview)


    }
}
