package com.it.myprojectfinal.model.body

data class BodyGetNoti (

    val notic_id: Int,
    val user_id: Int,
    val notic_topic: String,
    val notic_detail: String,
    val notic_type: String,
    val notic_voilent: String,
    val notic_location: String,
    val notic_status: String,
    val notic_steps: String,
    val notic_lat: String,
    val notic_long: String,
    val notic_time: String
)
data class BodyGetUserNoti(
    val userId: String
)
