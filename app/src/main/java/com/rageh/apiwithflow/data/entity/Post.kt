package com.rageh.apiwithflow.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Post constructor(
    @ColumnInfo(name = "user_id")
    @SerializedName("userId")
    @Expose
    val userId: Long = 0,
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    val id: Long = 0,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @ColumnInfo(name = "body")
    @SerializedName("body")
    @Expose
    val body: String? = null
)
