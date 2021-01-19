package com.rageh.apiwithflow.data.api.entity

import androidx.databinding.ObservableField

data class Result<out T>(
    val status: ObservableField<Status>,
    val data: T?,
    val msg: String?
) {
    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(ObservableField(Status.SUCCESS), data, null)
        }

        fun <T> error(msg: String, data: T? = null): Result<T> {
            return Result(ObservableField(Status.ERROR), data, msg)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(ObservableField(Status.LOADING), data, null)
        }
    }
}