package com.it.myprojectfinal.model.response

data class ResponseGetTambon(
    val message: List<MessageTambon>,
    val status: Int
)

data class MessageTambon(
    val amphur_id: Int,
    val tambon_name: String
)