package com.it.myprojectfinal.model.response

data class ResponseGetAmphur(
    val `data`: List<Data>,
    val status: Int
)

data class Data(
    val amphur_id: Int,
    val amphur_name: String
)