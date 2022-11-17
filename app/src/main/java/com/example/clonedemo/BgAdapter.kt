package com.example.clonedemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clonedemo.databinding.PagerBgLayoutBinding

class BgAdapter(activity: NestlePagerActivity):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 10
    }

    override fun createFragment(position: Int): Fragment {
        return BackgroundPageFragment()
    }
    class BackgroundPageFragment:Fragment() {
        lateinit var mPagerBgLayoutBinding: PagerBgLayoutBinding
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            mPagerBgLayoutBinding = PagerBgLayoutBinding.inflate(inflater,container,false)
            return mPagerBgLayoutBinding.root
        }
    }
}