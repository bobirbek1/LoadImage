package com.idrok.loadimage

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*
import java.lang.Exception

class MyAdapter(
    private val imageurl: ArrayList<String>,
    private val glide: RequestManager? = null,
    private val picasso: Picasso? = null
) : RecyclerView.Adapter<MyAdapter.VH>() {
    private var counter = 0
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        context = parent.context
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (glide != null){
            glide.load(imageurl[position])
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        e?.printStackTrace()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        counter++
                        if (counter == imageurl.size)
                            Toast.makeText(context,"All images downloaded!",Toast.LENGTH_SHORT).show()
                        return false
                    }

                })
                .into(holder.itemView.iv_item)
        } else {
            picasso?.load(imageurl[position])?.placeholder(R.drawable.place_holder)?.error(R.drawable.place_holder)
            ?.into(holder.itemView.iv_item,object : Callback{
                override fun onSuccess() {
                        counter++
                    if (counter == imageurl.size)
                        Toast.makeText(context,"All images downloaded!",Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Exception?) {
                    e?.printStackTrace()
                }

            })
        }
    }

    override fun getItemCount(): Int = imageurl.size

    class VH(view: View) : RecyclerView.ViewHolder(view) {

    }

}