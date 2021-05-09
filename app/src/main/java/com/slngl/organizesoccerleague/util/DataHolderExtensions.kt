package com.slngl.organizesoccerleague.util

import androidx.lifecycle.MutableLiveData
import com.slngl.organizesoccerleague.base.DataHolder

inline fun <T, Z> DataHolder<T>.mapAndResponseIfSuccess(mapper: (T) -> (Z)): DataHolder<Z> {
    return when (this) {
        is DataHolder.Success -> {
            DataHolder.Success(mapper(this.data))
        }
        is DataHolder.Error -> {
            this
        }
    }
}

fun <T> DataHolder<T>.toLiveData(
    mutable: MutableLiveData<T>,
    errorMessage: MutableLiveData<String>? = null
) {

    when (this) {
        is DataHolder.Success -> {
            this.data.let {
                mutable.postValue(it)
            }
        }
        is DataHolder.Error -> {
            this.message.let {
                if (errorMessage != null) errorMessage.postValue(it)
            }
        }
    }
}