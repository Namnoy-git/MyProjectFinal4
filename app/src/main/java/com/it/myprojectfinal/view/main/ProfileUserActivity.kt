package com.it.myprojectfinal.view.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.it.myprojectfinal.R
import com.it.myprojectfinal.controller.Utils
import com.it.myprojectfinal.model.response.DataList
import com.it.myprojectfinal.model.response.ResponseProfile
import com.it.myprojectfinal.model.response.ResponseProfileBody
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.ui.adapter.AdapterProfile
import com.it.myprojectfinal.ui.adapter.ImageViewPagerNotiAdapter
import com.it.myprojectfinal.ui.notifications.PresenterFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_profile_user.*



class ProfileUserActivity : AppCompatActivity() {

    var mPreferrences = Preferrences(this)
    val selectProfile = MainPresenter()
    private var imageViewAdapter: AdapterProfile? = null
    var mResponseProfile = ArrayList<ResponseProfileBody>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        setApi()

      btn_back_more.setOnClickListener {
          finish()
      }

        btnEditProfile.setOnClickListener {
            val i = Intent(this, EditProfileUserActivity::class.java)

            mPreferrences.getUserId() ?: ""
            i.putExtra("name", TV_Name.text.toString())
            i.putExtra("username", TV_Username.text.toString())
            i.putExtra("password", TV_Password.text.toString())
            i.putExtra("email", TV_Email.text.toString())
            i.putExtra("address", TV_Address.text.toString())
            i.putExtra("phone", TV_Phone.text.toString())


            startActivity(i)
        }
    }



    private fun setApi() {

        var imagDetail: ImageView = findViewById(R.id.ImViewProfile)

        selectProfile.SelectUser(
            mPreferrences.getUserId() ?: "",

            { profile ->

                TV_Name.text = profile.message.user_name
                TV_Username.text = profile.message.user_username
                TV_Password.text = profile.message.user_password
                TV_Email.text = profile.message.user_email
                TV_Address.text = profile.message.user_address
                TV_Phone.text = profile.message.user_phone

                Picasso.get()
            .load(Utils.BaseUrl+ "/uploadregis/")
            .into(imagDetail)

            }, {
            })


//
    }


}


