package com.rageh.apiwithflow.data.repository.base

import com.rageh.apiwithflow.data.api.GeneralErrorHandler
import com.rageh.apiwithflow.data.api.entity.ErrorEntity
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class BaseRepo {
    @Inject
    lateinit var errorHandler: GeneralErrorHandler

    @Inject
    lateinit var networkUtils: NetworkUtils

    protected fun loadFromApi(api: () -> Flow<Any>) =
        if (networkUtils.isNetworkAvailable()) {
            api.invoke().catch {
                it.printStackTrace()
                emit(it)
            }.transform {
                withContext(Dispatchers.IO) {
                    if (it is Exception) {
                        emit(
                            Resource.error(
                                errorHandler.getErrorMessage(errorHandler.getError(it)),
                                it
                            )
                        )
                    } else {
                        emit(Resource.success(it))
                    }
                }
            }.flowOn(Dispatchers.IO)
        } else {
            flow { emit(Resource.error<Nothing>(errorHandler.getErrorMessage(ErrorEntity.Network))) }
        }
}