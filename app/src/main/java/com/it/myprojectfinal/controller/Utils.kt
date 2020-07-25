package com.it.myprojectfinal.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder


class Utils {
    fun getGson(): Gson? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("M/d/yy hh:mm a")
        return gsonBuilder.create()
    }
}