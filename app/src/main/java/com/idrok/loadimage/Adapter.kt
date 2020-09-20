package com.idrok.loadimage

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*

class Adapter() : RecyclerView.Adapter<Adapter.VH>() {

    private var listBitmap = arrayListOf<Bitmap>()

    fun addBitmap(bitmap: Bitmap){
        listBitmap.add(bitmap)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int = listBitmap.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.iv_item.setImageBitmap(listBitmap[position])
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

    }

}