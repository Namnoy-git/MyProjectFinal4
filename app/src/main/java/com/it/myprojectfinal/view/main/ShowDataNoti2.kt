package com.it.myprojectfinal.view.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.it.myprojectfinal.R
import com.it.myprojectfinal.controller.Utils
import com.it.myprojectfinal.model.body.BodyImage
import com.it.myprojectfinal.model.response.ResponseInsert
import com.it.myprojectfinal.model.response.ResponseUploadImage
import com.it.myprojectfinal.ui.adapter.ImageViewPagerAdapter
import com.it.myprojectfinal.ui.notifications.PresenterFragment
import com.it.myprojectfinal.view.login.LoginActivity
import com.tangxiaolv.telegramgallery.GalleryActivity
import com.tangxiaolv.telegramgallery.GalleryConfig
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_show_data_noti2.*
import kotlinx.android.synthetic.main.activity_show_data_noti2.button_openimage
import kotlinx.android.synthetic.main.activity_show_data_noti2.viewPager
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ShowDataNoti2 : AppCompatActivity() {

    var PERMISSION_CODE = 1001
    var PICK_IMAGE_MULTIPLE = 1001
    private var imageViewPagerAdapter: ImageViewPagerAdapter? = null
    val mMainPresenter = MainPresenter()

    var uploadImage = ArrayList<BodyImage.Data>()
    var photos: List<String> = ArrayList()
    val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data_noti2)


        btn_back_image.setOnClickListener {
            finish()
        }

        button_openimage.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }

        }

        button_upload.setOnClickListener {

            mMainPresenter.upLoadImage(
                BodyImage(uploadImage),

                this::onSuccessSubscribe,
                this::onErrorSubscribe
            )

            /*val hashMap = HashMap<String, String>()
            hashMap["userID"] = intent.extras?.getString("userID").toString()
            hashMap["topic"] = intent.extras?.getString("topic").toString()
            hashMap["type"] = intent.extras?.getString("type").toString()
            hashMap["level"] = intent.extras?.getString("level").toString()
            hashMap["detail"] = intent.extras?.getString("detail").toString()
            hashMap["location"] = intent.extras?.getString("location").toString()*/


       /*     mMainPresenter.InsertNotiMainPersenterRx(hashMap) { res, error ->
                if (error == "") {
                    notic_id = res!!.notic_id.toString()

                    val json: String = Utils().getGson()!!.toJson(BodyImage(uploadImage))
                    Log.d("MainPresenter : json ", json)

                    mMainPresenter.upLoadImage(
                        res.notic_id,
                        BodyImage(uploadImage),

                        this::onSuccessSubscribe,
                        this::onErrorSubscribe
                    )
                }
            }*/


//            InPersenter.InsertNotiMainPersenterRx(
//                edt_topic.text.toString(),
//                spinner_type.selectedItem.toString(),
//                spinner_leval.selectedItem.toString(),
//                edit_detail.text.toString(),
//                edit_location.text.toString()
////                edit5.text.toString()
////                edit8.text.toString()
////                edit9.text.toString()
//
//
//            )

        }


    }

    private fun pickImageFromGallery() {
        val config = GalleryConfig.Build()
            .limitPickPhoto(12)
            .singlePhoto(false)
            .hintOfPick("this is pick hint")
            .filterMimeTypes(arrayOf("image/jpeg"))
            .build()
        GalleryActivity.openActivity(this@ShowDataNoti2, PICK_IMAGE_MULTIPLE, config)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try { // When an Image is picked
            var encodedImage: String

            photos = data!!.getSerializableExtra(GalleryActivity.PHOTOS) as List<String>

            val viewPager = findViewById<ViewPager>(R.id.viewPager)

            // for(i in photos){
            imageViewPagerAdapter =
                ImageViewPagerAdapter(this@ShowDataNoti2, photos as ArrayList<String>)
            //  }
            viewPager.setAdapter(imageViewPagerAdapter)

            val indicator = findViewById<CirclePageIndicator>(R.id.indicator).also {
                it.setViewPager(viewPager)
            }
            val density = resources.displayMetrics.density
            indicator.radius = 5 * density


            //วนเอาที่รูปที่เลือกจาก gallery มา
            for (i in photos) {

                val file = File(i)

                if (file.absolutePath != "") {

                    //เอารูปมาแปลงเป็น bitmap
                    val myBitmap = BitmapFactory.decodeFile(file.absolutePath)
                    if (myBitmap != null) {
                        val byteArrayOutputStream =
                            ByteArrayOutputStream()
                        myBitmap.compress(
                            Bitmap.CompressFormat.JPEG,
                            70,
                            byteArrayOutputStream
                        )

                        //เแปลงรูปจาก bitmap เป็น base 64
                        val byteArrayImage =
                            byteArrayOutputStream.toByteArray()
                        encodedImage = encodeToString(
                            byteArrayImage,
                            DEFAULT
                        )

                        Log.d("a9a20as8da", intent.extras?.getInt("notic_id").toString())
                        val bodyImage =
                            BodyImage.Data(
                                intent.extras?.getInt("notic_id").toString(),
                                file.name, "data:image/jpeg;base64,$encodedImage")

                        // val json: String = Utils().getGson()!!.toJson(bodyImage)
                        //Log.d("ShowDataNoti2 : json ",json)

                        uploadImage.add(bodyImage)
                    }
                }
            }


        } catch (e: Exception) {
            //Log.d("ShowDataNoti2", e.message)
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                .show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onSuccessSubscribe(responseData: ResponseUploadImage) {
        /*  val i = Intent(this, LoginActivity::class.java)
          startActivity(i)*/
        Toast.makeText(this, responseData.message, Toast.LENGTH_SHORT).show()

        //ล้างรูปออก
        viewPager.setAdapter(null)
        photos = ArrayList()
        uploadImage = ArrayList()



        val i = Intent(this,MainActivity::class.java)
        startActivity(i)

    }

    private fun onErrorSubscribe(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("ShowDataNoti2", message)
    }


}



