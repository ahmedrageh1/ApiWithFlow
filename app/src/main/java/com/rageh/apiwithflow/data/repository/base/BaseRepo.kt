package com.rageh.apiwithflow.data.repository.base

import com.rageh.apiwithflow.data.api.GeneralErrorHandler
import com.rageh.apiwithflow.data.api.entity.ErrorEntity
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.util.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class BaseRepo {
    @Inject
    lateinit var errorHandler: GeneralErrorHandler

    @Inject
    lateinit var networkUtils: NetworkUtils

    protected fun loadFromApi(api: suspend () -> Any) =
        if (networkUtils.isNetworkAvailable()) {
            flow {
                val result = api.invoke()
                emit(Resource.success(result))
            }.catch { e ->
                e.printStackTrace()
                emit(Resource.error(errorHandler.getErrorMessage(errorHandler.getError(e)), e))
            }.flowOn(Dispatchers.IO)
        } else {
            flow { emit(Resource.error<Nothing>(errorHandler.getErrorMessage(ErrorEntity.Network))) }.flowOn(
                Dispatchers.IO
            )
        }
}