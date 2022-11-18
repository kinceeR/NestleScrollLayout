package com.example.clonedemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.coordinatorlayout.widget.CoordinatorLayout

class TransCoordinatorLayout(context: Context, attributeSet: AttributeSet) :
    CoordinatorLayout(context, attributeSet),BrotherLayoutObserver {
    private lateinit var appBarLayout:MineAppbarLayout
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
         appBarLayout = findViewWithTag<MineAppbarLayout>("appbarLayout")
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        ev?.apply {
            val isInView =
                isPointInChildBounds(findViewWithTag("transLayout"), ev.x.toInt(), ev.y.toInt())
            return if (isInView  && !appBarLayout.isConsumed) {
                false
            }else{
                Log.d("BgTouchLayout", "dispatchTouchEvent: ")
                //本层处理
                super.dispatchTouchEvent(ev)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun notifyIgnore() {
        Log.d("TAG", "notifyIgnore: ")
        appBarLayout.setExpanded(expanded = false, animate = true)
//        val offsetY= pinToolbar.measuredHeight - appBarLayout.measuredHeight
//        val behavior = (appBarLayout.layoutParams as CoordinatorLayout.LayoutParams).behavior as AppBarLayout.Behavior
//        behavior.onNestedPreScroll(this, appBarLayout, appBarLayout, 0, 0-offsetY, intArrayOf(0,0),  0)
    }
}