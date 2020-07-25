package com.it.myprojectfinal.view.main

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.body.BodyImage
import com.it.myprojectfinal.model.response.ResponseInsert
import com.it.myprojectfinal.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_insert_main.*
import kotlinx.android.synthetic.main.activity_show_data_noti.*
import java.io.ByteArrayOutputStream
import java.io.File

class InsertActivity : AppCompatActivity() {

    val REQUEST_CODE = 200
    val mMainPersenter = MainPresenter()
//    var uploadImage = ArrayList<BodyImage.Data>()
//    var photos : List<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_main)
        setapi()


    floatingActionButton5.setOnClickListener {
            openGalleryForImages()
        }


    }


    private fun openGalleryForImages() {

        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Pictures")
                , REQUEST_CODE
            )
        }
        else { // For latest versions API LEVEL 19+
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE);
        }

    }

    private fun setapi() {
        btn_insert.setOnClickListener{
            mMainPersenter.InsertMainPersenterRx(
                edt_username.text.toString(),
                edt_password.text.toString(),
                edt_phone.text.toString(),

                this::onSuccessSubscribe,
                this::onErrorSubscribe)
        }

        cancle.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var encodedImage: String
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            // if multiple images are selected
            if (data?.getData() != null) {
                var imageUri: Uri? = data.data
                header_cover_image.setImageURI(imageUri)

            }

//            for (i in photos) {
//                val file = File(i)

//                if (file.absolutePath != "") {

//                    //เอารูปมาแปลงเป็น bitmap
//                    val myBitmap = BitmapFactory.decodeFile(file.absolutePath)
//                    if (myBitmap != null) {
//                        val byteArrayOutputStream =
//                            ByteArrayOutputStream()
//                        myBitmap.compress(
//                            Bitmap.CompressFormat.JPEG,
//                            70,
//                            byteArrayOutputStream
//                        )
//
//                        //เแปลงรูปจาก bitmap เป็น base 64
//                        val byteArrayImage =
//                            byteArrayOutputStream.toByteArray()
//                        encodedImage = Base64.encodeToString(
//                            byteArrayImage,
//                            Base64.DEFAULT
//                        )
//                        val bodyImage =
//                            BodyImage.Data(file.name, "data:image/jpeg;base64,$encodedImage")
//
//                        // val json: String = Utils().getGson()!!.toJson(bodyImage)
//                        //Log.d("ShowDataNoti2 : json ",json)
//
//                        uploadImage.add(bodyImage)
//                    }
//                }
//            }
//        }
            }
        }
    
        private fun onSuccessSubscribe(responseData: ResponseInsert) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)

        }

        private fun onErrorSubscribe(message: String) {

        }
    }