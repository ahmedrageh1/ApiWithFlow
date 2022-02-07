package com.rageh.apiwithflow.domain.handler

import com.rageh.apiwithflow.domain.contract.PostsContact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsHandler @Inject constructor(private val repo: PostsContact) {

    fun getPosts() = repo.getPosts()
}