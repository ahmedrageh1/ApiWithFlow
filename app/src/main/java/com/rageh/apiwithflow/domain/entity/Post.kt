package com.rageh.apiwithflow.domain.entity

data class Post constructor(
    val userId: Long = 0,
    val id: Long = 0,
    val title: String? = null,
    val body: String? = null
)
