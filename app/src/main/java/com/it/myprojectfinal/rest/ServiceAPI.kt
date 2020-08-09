package com.it.myprojectfinal.rest


import com.it.myprojectfinal.model.body.*
import com.it.myprojectfinal.model.response.*
import io.reactivex.Observable
import retrofit2.http.*


interface ServiceAPI {

    //login
    @POST("/login")
    fun doLogin(@Body body: BodyLogin?):Observable<ResponseLogin>

    // get เเสดงข้อมูล
    @POST("/usernoti")
    fun doGetNoti(@Body body: BodyGetUserNoti?):Observable<ResponseGetNoti>

    @POST("/userprofile")
    fun doGetProfile(@Body body: BodyProfile?):Observable<ResponseProfile>


    //post เพิ่มข้อมูล
    @POST("/regis")
    fun doInsert(@Body body: BodyInsert):Observable<ResponseInsert>

    //post เพิ่มข้อมูลตาราง noti
    @POST("/noti")
    fun doInsertnoti(@Body body: BodyInsertNoti):Observable<ResponseInsertNoti>


    @POST("/uploadmultiple")
    fun doUploadImg(@Body body: BodyImage):Observable<ResponseUploadImage>

    @POST("/uploadimageuser")
    fun doUploadImgUser(@Body body: BodyImageUser):Observable<ResponseUploadImage>

//    @POST("/regisimage")
//    fun doUserImg(@Body body: BodyUserImg):Observable<ResponseUploadImage>

    @DELETE("/noti/{id}")
    fun doDelete(@Path("id")id:Int):Observable<ResponseGetNoti>

    @PUT("/user/{id}")
    fun doUpdateuser(@Path("id") userId: String, @Body body: BodyUpdateUser?):Observable<ResponseProfile>

    //get image
    @GET("/getimage/{id}")
    fun dogetimage(@Path("id") userId: String): Observable<ResponseGetImageNoti>

    @GET("/getamphur")
    fun doGetAmphur(): Observable<ResponseGetAmphur>


}