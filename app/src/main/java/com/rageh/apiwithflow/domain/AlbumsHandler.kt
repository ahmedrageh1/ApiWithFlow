package com.rageh.apiwithflow.domain

import com.rageh.apiwithflow.data.repository.AlbumsRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsHandler @Inject constructor(private val repo: AlbumsRepo) {

    fun getAlbums() = repo.getAlbums()
}