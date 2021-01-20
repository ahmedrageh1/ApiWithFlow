package com.rageh.apiwithflow.data.api.entity

import androidx.databinding.ObservableField

data class Resource<out T>(
    val status: ObservableField<Status>,
    val data: T?,
    val msg: String?
) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(ObservableField(Status.SUCCESS), data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(ObservableField(Status.ERROR), data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(ObservableField(Status.LOADING), data, null)
        }
    }
}