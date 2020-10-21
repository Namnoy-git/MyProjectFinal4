package com.it.myprojectfinal.model.response

data class ResponseCheckNoti2(
    val message: MessageCheck2,
    val status: Int
)

data class MessageCheck2(
    val locality: String,
    val message: String,
    val status: String,
    val u_id: String
)