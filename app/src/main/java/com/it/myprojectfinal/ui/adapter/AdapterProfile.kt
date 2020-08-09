package com.it.myprojectfinal.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.it.myprojectfinal.R
import com.it.myprojectfinal.controller.Utils
import com.squareup.picasso.Picasso
import java.util.ArrayList

class AdapterProfile(private val ctx: Context, var mArrayUri: ArrayList<String>) : PagerAdapter() {
    // var context: Context
    //var images: IntArray
    var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return mArrayUri.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        val itemView: View = layoutInflater!!.inflate(R.layout.activity_profile_user, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.ImViewProfile) as ImageView

        Picasso.get()
            .load(Utils.BaseUrl+"/uploadregis/"+mArrayUri[position])
            .into(imageView)

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