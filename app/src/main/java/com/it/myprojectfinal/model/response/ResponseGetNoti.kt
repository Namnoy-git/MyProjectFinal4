package com.it.myprojectfinal.model.response



data class ResponseGetNoti(
    val `data`: List<DataList>,
    val status: Int
)

data class ResponseGetImageNoti(
    val `message`: List<DataImage>,
    val status: Int
)

data class DataImage(
    val notic_id: Int,
    val img_normal: String
)

data class DataList(

    val notic_id: Int,
    val notic_detail: String,
    val notic_lat: String,
    val notic_amphur: String,
    val notic_tambon :String,
    val notic_long: String,
    val notic_status: String,
    val notic_steps: String,
    val notic_time: String,
    val notic_topic: String,
    val notic_type: String,
    val notic_voilent: String,
    val user_id: String,
    val img: String
)