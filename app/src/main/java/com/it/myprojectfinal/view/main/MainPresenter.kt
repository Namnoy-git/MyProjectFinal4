package com.it.myprojectfinal.view.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.it.myprojectfinal.model.body.*
import com.it.myprojectfinal.model.response.*

import com.it.myprojectfinal.rest.DataModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.TimeUnit

class MainPresenter {

    var mDisposable: Disposable? = null


//    fun InsertNotiMainPersenterRx(
//        hashMap: HashMap<String, String>,
//        dataResponse: (ResponseInsertNoti?, String) -> Unit
//        //  MessageError: (String) -> Unit
//    ) {
//
//
//        mDisposable =
//            DataModule.myAppService.doInsertnoti(
//                BodyInsertNoti(
//                    hashMap["userID"].toString(),
//                    hashMap["topic"].toString(),
//                    hashMap["type"].toString(),
//                    hashMap["level"].toString(),
//                    hashMap["detail"].toString(),
//                    hashMap["location"].toString(),
//                    hashMap["lat"].toString(),
//                    hashMap["long"].toString()
//
//                )
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableObserver<ResponseInsertNoti>() {
//
//                    override fun onComplete() {
//
//                    }
//
//                    override fun onNext(response: ResponseInsertNoti) {
//                        Log.d("messageInsertNoti", response.toString())
//                        dataResponse.invoke(response, "")
//
//                    }
//
//                    override fun onError(e: Throwable) {
//                        Log.d("messageInsertNoti", e.message!!.toString())
//                        dataResponse.invoke(null, e.message!!)
//                    }
//                })
//
//    }

    //Rx Insert user

    //Rx Insert user
    fun SelectUser(
        userId: String,
        dataResponse: (ResponseProfile) -> Unit,
        MessageError: (String) -> Unit
    ) {
        mDisposable =
            DataModule.myAppService.doGetProfile(BodyProfile(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseProfile>() {

                    override fun onComplete() {

                    }

                    override fun onNext(response: ResponseProfile) {
                        Log.d("messageInsert", response.toString())
                        dataResponse.invoke(response)

                    }

                    override fun onError(e: Throwable) {
                        Log.d("messageInsert", e.message!!.toString())
                        MessageError.invoke(e.message!!)
                    }
                })

    }

    fun DeleteNotiPersenterRx(
        id: Int,
        datarResponse: (ResponseGetNoti) -> Unit,
        MessageError: (String) -> Unit
    ) {
        mDisposable = DataModule.myAppService.doDelete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseGetNoti>() {

                override fun onComplete() {

                }

                override fun onNext(response: ResponseGetNoti) {
                    Log.d("messagedelete", response.toString())
                    datarResponse.invoke(response)

                }

                override fun onError(e: Throwable) {
                    Log.d("messagedelete", e.message)
                    MessageError.invoke(e.message!!)
                }
            })

    }


    fun upLoadImage(
        bodyImage: BodyImage,
        dataResponse: (ResponseUploadImage) -> Unit,
        MessageError: (String) -> Unit
    ) {

        /*  val json: String = Utils().getGson()!!.toJson(bodyImage)
          Log.d("a9a20as8da", json)*/

        mDisposable =
            DataModule.myAppService.doUploadImg(bodyImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseUploadImage>() {
                    override fun onComplete() {

                    }

                    override fun onNext(response: ResponseUploadImage) {
                        Log.d("messageInsertImage", response.isSuccessful.toString())
                        if (response.isSuccessful) {
                            dataResponse.invoke(response)
                        } else {
                            MessageError.invoke("")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d("messageInsertImage", e.message!!.toString())
                        MessageError.invoke(e.message!!)
                    }
                })
    }


    @SuppressLint("CheckResult")
    fun UpdateUserImagePersenterRx(
        userId: String,
        user_name: String,
        user_username: String,
        user_password: String,
        user_email: String,
        user_address: String,
        user_phone: String,
        file: File,

        res: (Boolean) -> Unit,
        function: () -> Unit
    ) {
        val encodedImagePic1: String
        val uploadImage = ArrayList<BodyUpdateUserImage.Data>()

        if (file.absolutePath != "") {
            val myBitmap = BitmapFactory.decodeFile(file.absolutePath)

            if (myBitmap != null) {
                Log.d("ASd6asd", myBitmap.toString())
                val byteArrayOutputStream =
                    ByteArrayOutputStream()
                myBitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    70,
                    byteArrayOutputStream
                )
                val byteArrayImage =
                    byteArrayOutputStream.toByteArray()
                encodedImagePic1 = Base64.encodeToString(
                    byteArrayImage,
                    Base64.DEFAULT
                )

                val uploadData = BodyUpdateUserImage.Data(
                    userId,
                    user_name,
                    user_username,
                    user_password,
                    user_email,
                    user_address,
                    user_phone,
                    file.name,
                    "data:image/jpeg;base64,$encodedImagePic1"
                )
                uploadImage.add(uploadData)
            }
            /*      val json: String = Utils().getGson()!!.toJson(uploadImage)
            Log.d("a9a20as8da", json)*/
        }
        mDisposable =
            DataModule.myAppService.doUpdateuserImage(userId, BodyUpdateUserImage(uploadImage))
                .subscribeOn(Schedulers.io())
                .timeout(20, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseUploadImage>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: ResponseUploadImage) {
                        if (t.isSuccessful) {
                            res.invoke(true)
                            Log.d("As85das1d", t.message)
                        } else {
                            res.invoke(false)
                        }
                    }

                    @SuppressLint("DefaultLocale")
                    override fun onError(e: Throwable) {
                        res.invoke(false)

                    }
                })


    }


    @SuppressLint("CheckResult")
    fun upLoadUserImage(
        user_name: String,
        user_username: String,
        user_password: String,
        user_email: String,
        user_address: String,
        user_phone: String,
        file: File,

        res: (Boolean) -> Unit
    ) {
        val encodedImagePic1: String
        val uploadImage = ArrayList<BodyImageUser.Data>()

        if (file.absolutePath != "") {
            val myBitmap = BitmapFactory.decodeFile(file.absolutePath)

            if (myBitmap != null) {
                Log.d("ASd6asd", myBitmap.toString())
                val byteArrayOutputStream =
                    ByteArrayOutputStream()
                myBitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    70,
                    byteArrayOutputStream
                )
                val byteArrayImage =
                    byteArrayOutputStream.toByteArray()
                encodedImagePic1 = Base64.encodeToString(
                    byteArrayImage,
                    Base64.DEFAULT
                )

                val uploadData = BodyImageUser.Data(
                    user_name,
                    user_username,
                    user_password,
                    user_email,
                    user_address,
                    user_phone,
                    file.name,
                    "data:image/jpeg;base64,$encodedImagePic1"
                )
                uploadImage.add(uploadData)
            }
            /*      val json: String = Utils().getGson()!!.toJson(uploadImage)
            Log.d("a9a20as8da", json)*/
        }
        mDisposable =
            DataModule.myAppService.doUploadImgUser(BodyImageUser(uploadImage))
                .subscribeOn(Schedulers.io())
                .timeout(20, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseUploadImage>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: ResponseUploadImage) {
                        if (t.isSuccessful) {
                            res.invoke(true)
                            Log.d("As85das1d", t.message)
                        } else {
                            res.invoke(false)
                        }
                    }

                    @SuppressLint("DefaultLocale")
                    override fun onError(e: Throwable) {
                        res.invoke(false)

                    }
                })


    }

    fun UpdateProfileInsRx(
        userId: String,
        name: String,
        username: String,
        password: String,
        email: String,
        address: String,
        phone: String,
        user_img: String,
        datarResponse: (ResponseUpdateProfile) -> Unit,
        MessageError: (String) -> Unit
    ) {
        mDisposable = DataModule.myAppService.doUpdateuserNoImage(userId,
            BodyUpdateProfile(
                name,
                username,
                password,
                email,
                address,
                phone,
                user_img

            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseUpdateProfile>() {

                override fun onComplete() {

                }

                override fun onNext(response: ResponseUpdateProfile) {
                    Log.d("messageUpdate", response.toString())
                    datarResponse.invoke(response)

                }

                override fun onError(e: Throwable) {
                    Log.d("messageUpdate", e.message!!.toString())
                    MessageError.invoke(e.message!!)
                }
            })

    }
}

