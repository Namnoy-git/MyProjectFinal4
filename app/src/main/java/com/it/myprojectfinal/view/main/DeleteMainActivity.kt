package com.it.myprojectfinal.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.ResponseGetNoti

import com.it.myprojectfinal.ui.home.HomeFragment

import kotlinx.android.synthetic.main.activity_delete_main.*
import kotlinx.android.synthetic.main.activity_insert_main.*

class DeleteMainActivity : AppCompatActivity() {
    val mMainPersenter = MainPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_main)
        setapi()

        cancel_Delete.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }

    }

    private fun setapi() {

            val id = intent.getIntExtra("id", 0)

            mMainPersenter.DeleteNotiPersenterRx(id,
                this::onSuccessSubscribe,
                this::onErrorSubscribe)
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)

    }



    private fun onSuccessSubscribe(responseData: ResponseGetNoti) {

    }

    private fun onErrorSubscribe(message:String) {

    }
}