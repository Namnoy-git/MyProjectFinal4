package com.it.myprojectfinal.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.it.myprojectfinal.R
import com.it.myprojectfinal.rest.local.Preferrences
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_profile_user.*

class EditProfileUserActivity : AppCompatActivity() {

    var mPreferrences = Preferrences(this)
    val updeteProfile = MainPresenter()

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

        btnEditConfirmProfile.setOnClickListener {
            val i = Intent(this,ProfileUserActivity::class.java)
            startActivity(i)
        }

    }
    private fun setApi() {
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
    }

}