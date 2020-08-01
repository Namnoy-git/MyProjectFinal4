package com.it.myprojectfinal.model.response

data class ResponseInsert(
    val message: MessageInsert,
    val status: Int
)

data class MessageInsert(

    val user_name: String,
    val user_username: String,
    val user_password: String,
    val user_email: String,
    val user_address: String,
    val user_phone: String
)