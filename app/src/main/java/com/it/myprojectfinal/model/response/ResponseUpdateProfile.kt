package com.it.myprojectfinal.model.response

data class ResponseUpdateProfile(
    val message: MessageProfileUpdate,
    val status: Int
)

data class MessageProfileUpdate(
    val user_address: String,
    val user_email: String,
    val user_name: String,
    val user_password: String,
    val user_phone: String,
    val user_username: String,
    val user_img: String
)