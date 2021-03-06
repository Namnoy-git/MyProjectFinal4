package com.it.myprojectfinal.model.response

data class ResponseNotiCheck(
    val message: List<MessageCheck>,
    val status: Int
)

data class MessageCheck(
    val notic_amphur: String,
    val notic_detail: String,
    val notic_id: Int,
    val notic_lat: String,
    val notic_long: String,
    val notic_status: String,
    val notic_steps: String,
    val notic_tambon: String,
    val notic_time: String,
    val notic_topic: String,
    val notic_type: String,
    val notic_voilent: String,
    val user_id: Int
)