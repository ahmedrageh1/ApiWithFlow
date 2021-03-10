package com.rageh.apiwithflow.data.repository

import com.rageh.apiwithflow.data.api.retrofit.Webservice
import com.rageh.apiwithflow.data.repository.base.BaseRepo
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepo @Inject constructor(private val webservice: Webservice) : BaseRepo() {

    fun getPosts() = loadFromApi(webservice::getPosts).map { it }


}