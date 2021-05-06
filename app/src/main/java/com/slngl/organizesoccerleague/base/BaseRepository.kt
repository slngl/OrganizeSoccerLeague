package com.slngl.organizesoccerleague.base

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


open class BaseRepository {

    suspend fun <T> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): DataHolder<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                if (response.isSuccessful) DataHolder.Success(response.body()!!)
                else {
                    val gson = GsonBuilder().create()
                    val errorResponse =
                        gson.fromJson(response.errorBody()?.string(), BaseError::class.java)
                    DataHolder.Error(
                        IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"),
                        errorResponse.error
                    )
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> { //Network Error
                        DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                    }
                    is HttpException -> {
                        val code = throwable.code()
                        DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                    }
                    else -> {
                        DataHolder.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
                    }
                }
            }
        }
    }

}