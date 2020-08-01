package com.it.myprojectfinal.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.it.myprojectfinal.R
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.ui.notifications.PresenterFragment
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_profile_user.*

class ProfileUserActivity : AppCompatActivity() {

    var mPreferrences = Preferrences(this)
    val selectProfile = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        setApi()


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

        selectProfile.SelectUser(
            mPreferrences.getUserId() ?: "",
            { profile ->
                TV_Name.text = profile.message.user_name
                TV_Username.text = profile.message.user_username
                TV_Password.text = profile.message.user_password
                TV_Email.text = profile.message.user_email
                TV_Address.text = profile.message.user_address
                TV_Phone.text = profile.message.user_phone
            }, {
            })


    }


}
