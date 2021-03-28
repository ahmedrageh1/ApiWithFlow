package com.rageh.apiwithflow.data.repository

import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.data.api.entity.Status
import com.rageh.apiwithflow.data.api.retrofit.Webservice
import com.rageh.apiwithflow.data.cache.dao.PostsDao
import com.rageh.apiwithflow.data.entity.Post
import com.rageh.apiwithflow.data.repository.base.BaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepo @Inject constructor(
    private val postsDao: PostsDao,
    private val webservice: Webservice
) : BaseRepo() {

    fun getPosts() = flow {
        getAPIFlow().collect { result ->
            if (result.status.get() == Status.SUCCESS) {
                cachePostsResult(result)
            } else {
                emit(result)
            }
        }
        getCacheFlow().collect { emit(it) }
    }

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