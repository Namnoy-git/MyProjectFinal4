package com.it.myprojectfinal.rest

import com.it.myprojectfinal.controller.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BaseUrl = Utils.BaseUrl

object DataModule {
    private var retrofit: Retrofit? = null
    private var httpClient: OkHttpClient.Builder? = null

    private fun getInstance(): Retrofit {

        if (retrofit == null) {
            httpClient = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
            // เรียกใช้ retrofit + rxjava2 เพื่อเชื่อมต่อ Api
            retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient!!.build())
                .build()
        }
        return retrofit as Retrofit

    }

    val mHttpRetrofit = getInstance()

    val myAppService = mHttpRetrofit.create(ServiceAPI::class.java)


}