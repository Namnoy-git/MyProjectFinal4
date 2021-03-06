package com.it.myprojectfinal.model.response

data class ResponseInsertNoti(
    val message: MessageInsertNoti,
    val notic_id: Int,
    val status: Int
)

data class MessageInsertNoti(
    val notic_id : String,
    val user_id : String,
    val notic_detail: String,
    val notic_lat: String,
    val notic_amphur: String,
    val notic_tambon : String,
    val notic_long: String,
//    val notic_status: String,
//    val notic_steps: String,
    val notic_time: String,
    val notic_topic: String,
    val notic_type: String,
    val notic_voilent: String
)