package com.example.clonedemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.abs

class MineAppbarLayout(context: Context, attributeSet: AttributeSet) :
    AppBarLayout(context, attributeSet) {
    var expandedFlag =true
    private lateinit var appBarLayout:MineAppbarLayout
    private lateinit var pinToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var collapsingLayout:CollapsingToolbarLayout
    override fun setExpanded(expanded: Boolean, animate: Boolean) {
        if(expanded==expandedFlag) return
        expandedFlag =expanded
        super.setExpanded(expanded, animate)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        appBarLayout = findViewWithTag<MineAppbarLayout>("appbarLayout")
        pinToolbar = findViewWithTag<androidx.appcompat.widget.Toolbar>("pinToolbar")
        collapsingLayout = findViewWithTag<CollapsingToolbarLayout>("collapsingLayout")
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val scrollFlag = (collapsingLayout.layoutParams as AppBarLayout.LayoutParams).scrollFlags
            Log.d("TAG", "verticalOffset: $verticalOffset,pinToolbar:${pinToolbar.measuredHeight},appbar:${appBarLayout.measuredHeight},scrollFlag=$scrollFlag")
            if(abs(verticalOffset)<50){// 预留容错
                expandedFlag = true
                notifyScrollFlag()
            }
            val distantY = pinToolbar.measuredHeight-appBarLayout.measuredHeight -verticalOffset
            if (abs(distantY) < 50) {// 预留容错
                notifyScrollFlag()
            }
        }
    }
    private fun notifyScrollFlag(){
        if(expandedFlag){
            val params = (collapsingLayout.layoutParams as AppBarLayout.LayoutParams)
            params.scrollFlags= AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            collapsingLayout.layoutParams = params
        }else{
            val params = (collapsingLayout.layoutParams as AppBarLayout.LayoutParams)
            params.scrollFlags= AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
            collapsingLayout.layoutParams = params
        }
    }

}