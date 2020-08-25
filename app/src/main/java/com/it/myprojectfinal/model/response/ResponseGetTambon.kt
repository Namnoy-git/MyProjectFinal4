package com.it.myprojectfinal.model.response

data class ResponseGetTambon(
    val `data`: List<DataGetTambon>,
    val status: Int
)

data class DataGetTambon(
    val amphur_id: String,
    val tambon_id: Int,
    val tambon_name: String
)