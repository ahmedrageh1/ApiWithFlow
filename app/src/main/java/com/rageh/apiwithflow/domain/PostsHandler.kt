package com.rageh.apiwithflow.domain

import com.rageh.apiwithflow.data.repository.PostsRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsHandler @Inject constructor(private val repo: PostsRepo)