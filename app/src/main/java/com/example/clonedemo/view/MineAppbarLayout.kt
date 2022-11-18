package com.example.clonedemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.abs

class MineAppbarLayout(context: Context, attributeSet: AttributeSet) :
    AppBarLayout(context, attributeSet) {
    var TAG ="MineAppbarLayoutTAG"
    var isConsumed =false //默认本层不处理，透传给兄弟下层
    var callExpandFlag =false
    private lateinit var appBarLayout:MineAppbarLayout
    private lateinit var pinToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var collapsingLayout:CollapsingToolbarLayout

    private var mOffsetChangedListenerFold = object :OnOffsetChangedListener{
        override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
            val distantY = appBarLayout!!.measuredHeight + verticalOffset
            if (distantY == 0 && callExpandFlag) {
                removeOnOffsetChangedListener(this)
                addOnOffsetChangedListener(mOffsetChangedListenerExpand)
            }
        }

    }
    private var mOffsetChangedListenerExpand= object :OnOffsetChangedListener{
        override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
            val scrollFlag = (collapsingLayout.layoutParams as AppBarLayout.LayoutParams).scrollFlags
            Log.d(TAG, "verticalOffset: $verticalOffset,pinToolbar:${pinToolbar.measuredHeight},appbar:${appBarLayout?.measuredHeight},scrollFlag=$scrollFlag")
            if(abs(verticalOffset)==0){
                //监听到完全展开，清除消耗事件标记和 expandCall标记
                isConsumed = false
                callExpandFlag = false
            }else{
                //监听到offset移动，代表本层消耗事件
                isConsumed = true
            }
        }
    }

    override fun setExpanded(expanded: Boolean, animate: Boolean) {
        if(callExpandFlag) return
        callExpandFlag = true
        removeOnOffsetChangedListener(mOffsetChangedListenerExpand)
        addOnOffsetChangedListener(mOffsetChangedListenerFold)
        super.setExpanded(expanded, animate)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        appBarLayout = findViewWithTag<MineAppbarLayout>("appbarLayout")
        pinToolbar = findViewWithTag<androidx.appcompat.widget.Toolbar>("pinToolbar")
        collapsingLayout = findViewWithTag<CollapsingToolbarLayout>("collapsingLayout")
        appBarLayout.addOnOffsetChangedListener(mOffsetChangedListenerExpand)
    }

}