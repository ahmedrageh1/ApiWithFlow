package com.rageh.apiwithflow.di

import android.content.Context
import androidx.room.Room
import com.rageh.apiwithflow.data.cache.CacheDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Inject
    @Provides
    @Singleton
    fun settingsProfilesDatabase(@ApplicationContext context: Context): CacheDatabase {
        return Room.databaseBuilder(
            context,
            CacheDatabase::class.java, "cache_db"
        )
            .build()
    }

    @Inject
    @Provides
    @Singleton
    fun postsDao(db: CacheDatabase) =
        db.posts()


}