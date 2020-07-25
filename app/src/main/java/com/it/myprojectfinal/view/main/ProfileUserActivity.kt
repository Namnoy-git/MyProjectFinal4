package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_profile_user.*

class ProfileUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        btnEditProfile.setOnClickListener {
            val i = Intent(this,EditProfileUserActivity::class.java)
            startActivity(i)
        }
    }
}
