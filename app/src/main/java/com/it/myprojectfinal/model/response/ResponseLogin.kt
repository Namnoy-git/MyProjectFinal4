package com.it.myprojectfinal.model.response

data class ResponseLogin(
    val message: Message,
    val status: Int
)

data class Message(
    val password: String,
    val token: String,
    val user_id: Int,
    val username: String
)