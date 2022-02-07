package com.rageh.apiwithflow.domain.entity

data class Photo constructor(
    val albumId: Long = 0,
    val id: Long = 0,
    val title: String? = null,
    val url: String?,
    val thumbnailUrl: String? = null
)