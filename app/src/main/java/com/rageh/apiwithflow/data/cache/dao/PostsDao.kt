package com.rageh.apiwithflow.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.rageh.apiwithflow.data.cache.base.BaseDao
import com.rageh.apiwithflow.data.entity.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao : BaseDao<Post> {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>
}