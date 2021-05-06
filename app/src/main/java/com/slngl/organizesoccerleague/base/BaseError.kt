package com.slngl.organizesoccerleague.base

data class BaseError(
    val error: String,
    val success: Boolean,
    val code: Int
)