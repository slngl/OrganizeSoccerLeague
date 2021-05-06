package com.slngl.organizesoccerleague.base

sealed class DataHolder<out T> {
    data class Success<out T>(val data: T) : DataHolder<T>()
    data class Error(val exception: Throwable?, val message: String = "An error occured") :
        DataHolder<Nothing>()
}