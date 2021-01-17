package com.rageh.apiwithflow.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post constructor(
    @SerializedName("userId")
    @Expose
    val userId: Long = 0,
    @SerializedName("id")
    @Expose
    val id: Long = 0,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("body")
    @Expose
    val body: String? = null
)
