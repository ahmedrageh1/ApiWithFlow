package com.rageh.apiwithflow.data.repository

import com.rageh.apiwithflow.data.api.retrofit.Webservice
import com.rageh.apiwithflow.data.cache.dao.PostsDao
import com.rageh.apiwithflow.data.model.Post
import com.rageh.apiwithflow.data.model.mapToEntity
import com.rageh.apiwithflow.data.repository.base.BaseRepo
import com.rageh.apiwithflow.domain.contract.PostsContact
import com.rageh.apiwithflow.domain.entity.Resource
import com.rageh.apiwithflow.domain.entity.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepo @Inject constructor(
    private val postsDao: PostsDao,
    private val webservice: Webservice
) : BaseRepo(), PostsContact {

    override fun getPosts() = callbackFlow<Resource<*>> {
        async(Dispatchers.IO) {
            getAPIFlow().collect { result ->
                if (result.status.get() == Status.SUCCESS) {
                    cachePostsResult(result)
                } else {
                    channel.send(result)
                }
            }
        }
        getCacheFlow().collect {
            channel.send(it)
        }
    }.map {
        if (it.status.get() == Status.SUCCESS) Resource.success((it.data as List<Post>).map { it.mapToEntity() })
        else it
    }.flowOn(Dispatchers.IO)

    private fun getAPIFlow() = loadFromApi(webservice::getPosts)

    private suspend fun getCacheFlow() = postsDao.getAllPosts().transform {
        if (it.isNotEmpty()) {
            emit(Resource.success(it))
        }
    }

    private suspend fun cachePostsResult(result: Resource<*>) {
        withContext(Dispatchers.IO) {
            if (result.status.get() == Status.SUCCESS) {
                //cache success result
                postsDao.insertAll(result.data as List<Post>)
            }
        }
    }


}