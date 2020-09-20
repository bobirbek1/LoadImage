package com.idrok.loadimage

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        setRv()
        btn_url.setOnClickListener {
            setRv()
        }
        btn_glide.setOnClickListener {
            setUpRvWithLibrary(Glide.with(this),null)
        }
        btn_picasso.setOnClickListener {
setUpRvWithLibrary(null,Picasso.get()) }

    }

    private fun setUpRvWithLibrary(glide: RequestManager?, picasso: Picasso?) {
        val adapter = MyAdapter(getImageUrl(),glide,picasso)
        rv_main.adapter = adapter
    }

    private fun setRv() {
        val adapter = Adapter()
        rv_main.adapter = adapter
        getImage(adapter)
    }


    private fun getImageUrl(): ArrayList<String> = arrayListOf(
        "https://i.pinimg.com/originals/82/eb/46/82eb465915758e9825cc1acddb192033.jpg",
        "https://i.pinimg.com/originals/43/94/23/4394235fd44c221d3c8547dba8891810.jpg",
        "https://i.pinimg.com/originals/5e/ec/33/5eec33203cd5d04264c4c5bad70a8339.jpg",
        "https://i.pinimg.com/originals/93/2f/27/932f2770dc6c28b974dae087a6fbe491.jpg",
        "https://i.pinimg.com/originals/92/06/23/92062324c5561a66f9565430fa25ba6e.jpg",
        "https://i.pinimg.com/originals/fa/6b/cf/fa6bcf2992cea30e3fea5b35008d4256.jpg",
        "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823_1280.jpg",
        "https://cdn.pixabay.com/photo/2015/09/09/16/05/forest-931706_1280.jpg",
        "https://cdn.pixabay.com/photo/2015/06/19/21/24/the-road-815297_1280.jpg",
        "https://cdn.pixabay.com/photo/2015/12/01/20/28/fall-1072821_1280.jpg",
        "https://cdn.pixabay.com/photo/2015/11/16/16/28/bird-1045954_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/08/11/23/48/italy-1587287_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/07/22/16/29/fog-1535201_1280.jpg",
        "https://cdn.pixabay.com/photo/2018/09/23/18/30/drop-3698073_1280.jpg",
        "https://cdn.pixabay.com/photo/2017/07/18/18/24/dove-2516641__480.jpg"
    )


    private fun getImage(adapter: Adapter) {
        for (imgUrl in getImageUrl()){
        Thread {
            val url = URL(imgUrl)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread {
                adapter.addBitmap(bitmap)
                counter++
                if (counter == getImageUrl().size){
                    showToast("All images downloaded")
                }
            }
        }.start()
        }

    }

    fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}

