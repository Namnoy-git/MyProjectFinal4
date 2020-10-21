package com.it.myprojectfinal.model.response

data class ResponseCheckNoti(
    val results: List<Result>
)

data class Result(
    val locality: String,
    val message: String,
    val nt_id: Int,
    val status: String,
    val u_id: Int
)