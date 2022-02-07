package com.rageh.apiwithflow.di

import com.rageh.apiwithflow.data.repository.AlbumsRepo
import com.rageh.apiwithflow.data.repository.PostsRepo
import com.rageh.apiwithflow.domain.contract.AlbumsContract
import com.rageh.apiwithflow.domain.contract.PostsContact
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getPostsRepo(repo: PostsRepo): PostsContact

    @Binds
    abstract fun getAlbumsRepo(repo: AlbumsRepo): AlbumsContract

}