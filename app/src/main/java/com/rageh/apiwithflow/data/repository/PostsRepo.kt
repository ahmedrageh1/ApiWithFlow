package com.rageh.apiwithflow.data.repository

import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.data.api.entity.Status
import com.rageh.apiwithflow.data.api.retrofit.Webservice
import com.rageh.apiwithflow.data.cache.dao.PostsDao
import com.rageh.apiwithflow.data.entity.Post
import com.rageh.apiwithflow.data.repository.base.BaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
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
        //load from cache
        emitAll(postsDao.getAllPosts().transform {
            if (it.isNotEmpty()) {
                emit(Resource.success(it))
            }
        })
        //load from api and update cached data
        //add conditions to control when data should be updated from api or use work manager in background task
        emitAll(loadFromApi(webservice::getPosts).transform {
            if (it.status.get() == Status.SUCCESS) {
                //cache success result
                withContext(Dispatchers.IO) {
                    postsDao.insertAll(it.data as List<Post>)
                }
            } else {
                //emit error only
                emit(it)
            }
        })


    }


}