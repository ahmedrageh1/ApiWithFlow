package com.rageh.apiwithflow.data.api

import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.data.api.entity.ErrorEntity
import com.rageh.apiwithflow.util.ResourcesResolver
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralErrorHandler @Inject constructor(private val resourcesResolver: ResourcesResolver) {

    fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                    //add any other error type here
                    // all the others will be treated as unknown error
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }

    fun getErrorMessage(errorEntity: ErrorEntity) = when (errorEntity) {
        ErrorEntity.Network -> resourcesResolver.getString(R.string.msg_network_error)
        ErrorEntity.NotFound -> resourcesResolver.getString(R.string.msg_not_found_error)
        ErrorEntity.AccessDenied -> resourcesResolver.getString(R.string.msg_auth_error)
        ErrorEntity.ServiceUnavailable -> resourcesResolver.getString(R.string.msg_unknown_error)
        ErrorEntity.Unknown -> resourcesResolver.getString(R.string.msg_unknown_error)
    }
}