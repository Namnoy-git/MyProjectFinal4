package com.it.myprojectfinal.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.ResponseGetImageNoti
import com.it.myprojectfinal.ui.adapter.ImageViewPagerAdapter
import com.it.myprojectfinal.ui.adapter.ImageViewPagerNotiAdapter
import com.it.myprojectfinal.ui.notifications.PresenterFragment
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_show_data_noti.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ShowDataNoti : AppCompatActivity() {

    var notiPersenter = PresenterFragment()
    private var imageViewPagerAdapter: ImageViewPagerNotiAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data_noti)
        setApi()

        btn_back_detall.setOnClickListener {
            finish()
        }

        val topic:String = intent.getStringExtra("notic_topic")
        val detail:String = intent.getStringExtra("notic_detail")
        val type:String = intent.getStringExtra("notic_type")
        val voilent:String = intent.getStringExtra("notic_voilent")
        val amphur:String = intent.getStringExtra("notic_amphur")
        val tambon:String = intent.getStringExtra("notic_tambon")
        val status:String = intent.getStringExtra("notic_status")
        val steps:String = intent.getStringExtra("notic_steps")
        val lat:String = intent.getStringExtra("notic_lat")
        val long:String = intent.getStringExtra("notic_long")
        val time:String = intent.getStringExtra("notic_time")

        Showtopic.text = topic
        Showtype.text = type
        Showleval.text = voilent
        Showdetail.text = detail
        Show_amphur.text = amphur
        Show_tambon.text = tambon
        Showhelp.text = status
        Showdetailhelp.text = steps
//        TV_LatShow.text = lat
//        TV_LongtShow.text = long
//        TV_TimetShow.text = time

    }

    private fun setApi(){
        val notic_id = intent.getStringExtra("notic_id")
        notiPersenter.GetImageNotiRx(notic_id,
            this::onSuccessSub,
            this::onErrorSub)
    }

    private fun onErrorSub(e: String) {

    }

    private fun onSuccessSub(res: ResponseGetImageNoti) {
        val photos = ArrayList<String>()
        for (i in res.message){
            photos.add(i.img_normal)
        }
//        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        val viewPager = findViewById<ViewPager>(R.id.viewpager_Show)

        imageViewPagerAdapter =
            ImageViewPagerNotiAdapter(this@ShowDataNoti, photos)
        viewPager.adapter = imageViewPagerAdapter

        val indicator = findViewById<CirclePageIndicator>(R.id.indicator).also {
            it.setViewPager(viewPager)
        }
        val density = resources.displayMetrics.density
        indicator.radius = 5 * density

//        dotsIndicator.setViewPager(viewPager)
    }


}
