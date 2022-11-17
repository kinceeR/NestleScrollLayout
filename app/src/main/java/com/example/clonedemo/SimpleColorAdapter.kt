package com.example.clonedemo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clonedemo.databinding.ColorfulItemLayoutBinding

class SimpleColorAdapter: RecyclerView.Adapter<SimpleColorAdapter.MineViewHolder>() {
    lateinit var mColorfulItemLayoutBinding: ColorfulItemLayoutBinding
    class MineViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineViewHolder {
        mColorfulItemLayoutBinding = ColorfulItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MineViewHolder(mColorfulItemLayoutBinding.root)
    }

    override fun getItemCount(): Int {
       return 100
    }

    override fun onBindViewHolder(holder: MineViewHolder, position: Int) {
        val colors = intArrayOf(
            0xFFBB86FC.toInt(),
            0xFF6200EE.toInt(),
            0xFF3700B3.toInt(),
            0xFF03DAC5.toInt(),
            0xFF018786.toInt(),
            )
        mColorfulItemLayoutBinding.cardView.setCardBackgroundColor(colors.random())
    }
}