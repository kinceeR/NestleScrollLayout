package com.example.clonedemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs

class BgTouchLayout(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet),BrotherLayoutObservable {
    var TAG ="BgTouchLayout"
    var ignoreFlag =false
    var scrollFlag = false
    private lateinit var mBrotherLayoutObserver: BrotherLayoutObserver
    private var mGestureDetector= GestureDetector(context,object :GestureDetector.OnGestureListener{
        override fun onDown(e: MotionEvent): Boolean {
            Log.d(TAG, "onDown: ")
            return true
        }

        override fun onShowPress(e: MotionEvent) {
            Log.d(TAG, "onShowPress: ")
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, "onSingleTapUp: ${e.action}")
            return true
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.d(TAG, "onScroll: e1=${e1.x},${e1.y} ,e2 =${e2.x},${e2.y}")
            Log.d(TAG, "onScroll: disx=$distanceX,disy=$distanceY")
            if(scrollFlag) return true
            ignoreFlag = abs(distanceX) < abs(distanceY) && distanceY > 0
            if(ignoreFlag) mBrotherLayoutObserver.notifyIgnore()
            scrollFlag = true
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, "onLongPress: ")
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d(TAG, "onScroll: e1=${e1.x},${e1.y} ,e2 =${e2.x},${e2.y}")
            Log.d(TAG, "onFling: vx=$velocityX,vy=$velocityY")
            scrollFlag = true
            ignoreFlag = false
            return true
        }

    })


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(ev!!)
//        Log.d(TAG, "dispatchTouchEvent: $isConsumed ,ev =${ev.action},x=${ev.x} ,y=${ev.y}")
        val result = if(!ignoreFlag){
            super.dispatchTouchEvent(ev)
        }else{
             false
        }
        if (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_CANCEL){
            ignoreFlag =false
            scrollFlag =false
        }
        return  result
    }

    override fun setObserver(brother: BrotherLayoutObserver) {
        mBrotherLayoutObserver = brother
    }

}