package com.it.myprojectfinal.model.response

data class ResponseInsert(
    val message: MessageInsert,
    val status: Int
)

data class MessageInsert(
    val user_password: String,
    val user_phone: String,
    val user_username: String
)