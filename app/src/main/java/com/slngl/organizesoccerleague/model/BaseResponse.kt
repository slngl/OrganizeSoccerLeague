package com.slngl.organizesoccerleague.model

data class BaseResponse<T>(
    val data: Data,
    val success: Boolean
)