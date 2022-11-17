package com.example.clonedemo.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs

class BgTouchLayout(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet),BrotherLayoutObservable {
    var mOldX=-1F
    var mOldY=-1F
    private lateinit var mBrotherLayoutObserver: BrotherLayoutObserver
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP || ev?.action == MotionEvent.ACTION_CANCEL) {
            mOldX=-1F
            mOldY=-1F
            return super.dispatchTouchEvent(ev)
        }
        ev?.apply {
            if (mOldX == -1F) mOldX = ev.x
            if (mOldY == -1F) mOldY = ev.y
            val deltaX = ev.x - mOldX
            val deltaY = ev.y - mOldY
            return if (Math.abs(deltaX) < Math.abs(deltaY) && abs(deltaX) < 50) {
                mBrotherLayoutObserver.notifyIgnore()
                false
            } else {
                super.dispatchTouchEvent(ev)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun setObserver(brother: BrotherLayoutObserver) {
        mBrotherLayoutObserver = brother
    }

}