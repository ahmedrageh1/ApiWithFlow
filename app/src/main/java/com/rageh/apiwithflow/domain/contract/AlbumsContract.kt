package com.rageh.apiwithflow.domain.contract

import com.rageh.apiwithflow.domain.entity.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumsContract {

    fun getAlbums(): Flow<Resource<*>>

    fun getPhotos(albumId: Long? = null): Flow<Resource<*>>

}