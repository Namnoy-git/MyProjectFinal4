package com.it.myprojectfinal.view.main

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.it.myprojectfinal.R
import com.it.myprojectfinal.controller.Utils
import com.it.myprojectfinal.rest.local.Preferrences
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_insert_main.*
import java.io.File
import java.lang.Exception

class EditProfileUserActivity : AppCompatActivity() {

    var imageName: File? = null
    private val PICK_IMAGE = 1001
    var mPreferrences = Preferrences(this)
    val updeteProfile = MainPresenter()
    val selectProfile = MainPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_user)
        setApi()


        TV_imageEdit.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, PICK_IMAGE)
        }

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
            val i  = Intent(this,ProfileUserActivity::class.java)
            startActivity(i)
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
            val imgUser = intent.getStringExtra("img")
            if(imageName != null) {
                updeteProfile.UpdateUserImagePersenterRx(
                    mPreferrences.getUserId() ?: "",
                    TV_EditName.text.toString(),
                    TV_EditUsername.text.toString(),
                    TV_EditPassword.text.toString(),
                    TV_EditEmail.text.toString(),
                    TV_EditAddress.text.toString(),
                    TV_EditPhone.text.toString(),
                    imageName!!,
                    {
                        Log.d("messagsUpdate", it.toString())
                        Toast.makeText(this, "บันทึกการเเก้ไขเรียบร้อยเเล้ว", Toast.LENGTH_LONG)
                            .show()
                        val i = Intent(this, ProfileUserActivity::class.java)
                        startActivity(i)
                        finish()
                    }, {

                    })
            }else {
                updeteProfile.UpdateProfileInsRx(
                    mPreferrences.getUserId() ?: "",
                    TV_EditName.text.toString(),
                    TV_EditUsername.text.toString(),
                    TV_EditPassword.text.toString(),
                    TV_EditEmail.text.toString(),
                    TV_EditAddress.text.toString(),
                    TV_EditPhone.text.toString(),
                    imgUser,
                    {
                        val i = Intent(this, ProfileUserActivity::class.java)
                        startActivity(i)
                        finish()
                    },
                    {

                    }
                )
            }
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("As6dasd", "1")
        if (PICK_IMAGE == requestCode && resultCode == Activity.RESULT_OK) {

            try {
                val pickedImage: Uri = data?.data!!

                val filePath = arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor =
                    this.contentResolver.query(pickedImage, filePath, null, null, null)!!
                cursor.moveToFirst()
                val imagePath: String = cursor.getString(cursor.getColumnIndex(filePath[0]))
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val bitmap = BitmapFactory.decodeFile(imagePath, options)

                Log.d("As5da1sda",File(imagePath).absolutePath )
                imageName = File(imagePath)

                Picasso.get().load(imageName!!).into(ImViewProfileEdit)

            }catch (e: Exception){
                e.printStackTrace()
            }

            // addImageQRCode.setImageBitmap(bitmap)


        }
    }

}