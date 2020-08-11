package com.it.myprojectfinal.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.it.myprojectfinal.R
import com.it.myprojectfinal.controller.Utils
import com.it.myprojectfinal.rest.local.Preferrences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_profile_user.*

class EditProfileUserActivity : AppCompatActivity() {

    var mPreferrences = Preferrences(this)
    val updeteProfile = MainPresenter()
    val selectProfile = MainPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_user)
        setApi()

        mPreferrences.getUserId() ?: ""
        val name = intent.getStringExtra("name")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val phone = intent.getStringExtra("phone")

        TV_EditName.setText(name)
        TV_EditUsername.setText(username)
        TV_EditPassword.setText(password)
        TV_EditEmail.setText(email)
        TV_EditAddress.setText(address)
        TV_EditPhone.setText(phone)


        btn_back_profile.setOnClickListener {
            finish()
        }

        btnEditConfirmProfile.setOnClickListener {
            val i = Intent(this,ProfileUserActivity::class.java)
            startActivity(i)
            finish()
        }

    }
    private fun setApi() {
        var imagDetail: ImageView = findViewById(R.id.ImViewProfileEdit)
        btnEditSaveProfile.setOnClickListener {
        updeteProfile.UpdateUserPersenterRx(
            mPreferrences.getUserId() ?: "",
            TV_EditName.text.toString(),

            TV_EditUsername.text.toString(),
            TV_EditPassword.text.toString(),
            TV_EditEmail.text.toString(),
            TV_EditAddress.text.toString(),
            TV_EditPhone.text.toString(),

            { profile ->

                val i = Intent(this, ProfileUserActivity::class.java)
                startActivity(i)
            }, {
            })
        }


        selectProfile.SelectUser(

            mPreferrences.getUserId() ?: "",
            {
                Picasso.get().load(Utils.BaseUrl+ "/uploadregis/"+it.message.user_img).into(imagDetail)
                Log.d("Image",it.message.user_img)
            },
            {

            }
        )


    }

}