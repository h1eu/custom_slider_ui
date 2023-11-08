package com.example.custom_slider_ui.layoutmanager

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class HorizontalLayoutManager(
    context: Context,
    private val minScaleDistanceFactor: Float = 1.5f,
    private val scaleDownBy: Float = 0.5f
): LinearLayoutManager(context, HORIZONTAL,false) {

    override fun onLayoutCompleted(state: RecyclerView.State?) =
        super.onLayoutCompleted(state).also { scaleChildren() }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ) = super.scrollHorizontallyBy(dx, recycler, state).also {
        if (orientation == HORIZONTAL) scaleChildren()
    }


    private fun scaleChildren(){
        val containerCenter = width / 2f
        val scaleDistanceThreshold = minScaleDistanceFactor * containerCenter

        for(i in 0 until childCount){
            val child = getChildAt(i)
            if (child != null){
                val childrenCenter = (child.left + child.right) / 2
                val distanceToCenter = abs(childrenCenter - containerCenter)
                val scaleDownAmount = (distanceToCenter / scaleDistanceThreshold).coerceAtMost(1f)
                val scale = 1f - scaleDownBy * scaleDownAmount
                child.scaleX = scale.coerceAtLeast(0.9f)
                child.scaleY = scale.coerceAtLeast(0.9f)



                val translationDirection = if (childrenCenter > containerCenter) -1 else 1
                val translationXFromScale = translationDirection * child.width * (1 - scale) / 2
                Log.e("nnb", "$i $translationXFromScale", )
                if (translationXFromScale > 0){
                    child.translationZ = translationXFromScale * -1
                    child.alpha = 0.5f
                }
                else if (translationXFromScale < 0){
                    child.translationZ = translationXFromScale
                    child.alpha = 0.5f
                }
                else{
                    child.alpha = 1f
                }

                child.translationX = translationXFromScale
            }
        }
    }
}