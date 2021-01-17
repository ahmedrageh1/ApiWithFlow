package com.rageh.apiwithflow.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo constructor(
    @SerializedName("albumId")
    @Expose
    val albumId: Long = 0,
    @SerializedName("id")
    @Expose
    val id: Long = 0,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String? = null
)