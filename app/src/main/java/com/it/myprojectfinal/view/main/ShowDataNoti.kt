package com.it.myprojectfinal.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.it.myprojectfinal.R
import kotlinx.android.synthetic.main.activity_show_data_noti.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ShowDataNoti : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data_noti)

        val topic:String = intent.getStringExtra("notic_topic")
        val detail:String = intent.getStringExtra("notic_detail")
        val type:String = intent.getStringExtra("notic_type")
        val voilent:String = intent.getStringExtra("notic_voilent")
        val location:String = intent.getStringExtra("notic_location")
        val status:String = intent.getStringExtra("notic_status")
        val steps:String = intent.getStringExtra("notic_steps")
        val lat:String = intent.getStringExtra("notic_lat")
        val long:String = intent.getStringExtra("notic_long")
        val time:String = intent.getStringExtra("notic_time")

        Showtopic.text = topic
        Showtype.text = type
        Showleval.text = voilent
        Showdetail.text = detail
        Showlacation.text = location
        Showhelp.text = status
        Showdetailhelp.text = steps
//        TV_LatShow.text = lat
//        TV_LongtShow.text = long
//        TV_TimetShow.text = time

    }
}
