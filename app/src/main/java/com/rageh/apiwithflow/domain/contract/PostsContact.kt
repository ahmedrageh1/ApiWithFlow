package com.rageh.apiwithflow.domain.contract

import com.rageh.apiwithflow.domain.entity.Resource
import kotlinx.coroutines.flow.Flow

interface PostsContact {

    fun getPosts(): Flow<Resource<*>>
}