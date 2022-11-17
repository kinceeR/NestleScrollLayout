package com.example.clonedemo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import com.example.clonedemo.databinding.NestlePagerBinding
import com.example.clonedemo.view.BrotherLayoutObservable
import com.example.clonedemo.view.BrotherLayoutObserver
import kotlin.random.Random

class NestlePagerActivity:AppCompatActivity() {
    lateinit var mNestlePagerBinding: NestlePagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNestlePagerBinding = NestlePagerBinding.inflate(layoutInflater)
        setContentView(mNestlePagerBinding.root)
        initView()
    }
    private fun initView(){
        mNestlePagerBinding.vpBg.adapter=BgAdapter(this)
        mNestlePagerBinding.containerRv.adapter = SimpleColorAdapter()
        mNestlePagerBinding.containerRv.layoutManager = GridLayoutManager(this,4).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if(position<=3){
                        1
                    }else if(position<=7){
                        2
                    }else {
                        4
                    }
                }

            }
        }
        bindObserver()
    }
    private fun bindObserver(){
        val observer = mNestlePagerBinding.root.findViewWithTag<ViewGroup>("observer") as BrotherLayoutObserver
        val observable =mNestlePagerBinding.root.findViewWithTag<ViewGroup>("observable") as BrotherLayoutObservable
        observable.setObserver(observer)
    }


}