package com.rageh.apiwithflow.domain.handler

import com.rageh.apiwithflow.domain.contract.AlbumsContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsHandler @Inject constructor(private val repo: AlbumsContract) {

    fun getAlbums() = repo.getAlbums()

    fun getPhotos(albumId: Long? = null) = repo.getPhotos(albumId)
}