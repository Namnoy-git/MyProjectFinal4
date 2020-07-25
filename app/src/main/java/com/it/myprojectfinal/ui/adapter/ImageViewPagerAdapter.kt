package com.it.myprojectfinal.ui.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.it.myprojectfinal.R
import java.util.*


class ImageViewPagerAdapter(private val ctx: Context, var mArrayUri: ArrayList<String>) : PagerAdapter() {
    // var context: Context
    //var images: IntArray
    var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return mArrayUri.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        val itemView: View = layoutInflater!!.inflate(R.layout.gv_item, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.ivGallery) as ImageView

        val myBitmap = BitmapFactory.decodeFile(mArrayUri[position])
        imageView.setImageBitmap(myBitmap)

        container.addView(itemView)

        return itemView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}