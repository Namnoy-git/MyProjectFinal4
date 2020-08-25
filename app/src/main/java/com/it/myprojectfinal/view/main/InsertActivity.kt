package com.it.myprojectfinal.view.main

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.body.BodyImage
import com.it.myprojectfinal.model.body.BodyImageUser
import com.it.myprojectfinal.model.response.ResponseInsert
import com.it.myprojectfinal.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_insert_main.*
import kotlinx.android.synthetic.main.activity_show_data_noti.*
import kotlinx.android.synthetic.main.activity_show_data_noti2.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get
import java.nio.file.Paths.get

class InsertActivity : AppCompatActivity() {

//    private lateinit var header_cover_image: ImageView
    private lateinit var imageName: File
    private val PICK_IMAGE = 1001
    val REQUEST_CODE = 200
    val mMainPersenter = MainPresenter()
    val imageUser = MainPresenter()
    var uploadImageuser = ArrayList<BodyImageUser.Data>()
    var photos : List<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_main)
        setapi()


    floatingActionButton5.setOnClickListener {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, PICK_IMAGE)
        }

        cancle.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }




    private fun setapi() {
        btn_insert.setOnClickListener {

            mMainPersenter.upLoadUserImage(
                edt_name.text.toString(),
                edt_username.text.toString(),
                edt_password.text.toString(),
                edt_email.text.toString(),
                edt_address.text.toString(),
                edt_phone.text.toString(),
                imageName

            ) {

                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                    Toast.makeText(this, "สมัครสมาชิกเรียบร้อยเเล้ว", Toast.LENGTH_SHORT).show()

            }

            cancle.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
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

                Picasso.get().load(imageName).into(header_cover_image)

            }catch (e: Exception){
                e.printStackTrace()
            }

            // addImageQRCode.setImageBitmap(bitmap)


        }
    }
    }