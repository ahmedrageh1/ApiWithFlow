package com.rageh.apiwithflow.data.repository.base

import com.rageh.apiwithflow.data.api.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

open class BaseRepo {

    protected fun loadFromApi(api: () -> Flow<Any>) =
        api.invoke().catch {
            it.printStackTrace()
            emit(it)
        }.transform {
            if (it is Exception) {
                emit(Result.error(it.localizedMessage, it))
            } else {
                emit(Result.success(it))
            }
        }
}