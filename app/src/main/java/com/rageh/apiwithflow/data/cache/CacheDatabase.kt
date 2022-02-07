package com.rageh.apiwithflow.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rageh.apiwithflow.data.cache.dao.PostsDao
import com.rageh.apiwithflow.data.model.Post

@Database(entities = [Post::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {

    abstract fun posts(): PostsDao

}