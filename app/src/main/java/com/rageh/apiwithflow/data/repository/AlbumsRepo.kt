package com.rageh.apiwithflow.data.repository

import com.rageh.apiwithflow.data.api.retrofit.Webservice
import com.rageh.apiwithflow.data.model.Album
import com.rageh.apiwithflow.data.model.Photo
import com.rageh.apiwithflow.data.model.mapToEntity
import com.rageh.apiwithflow.data.repository.base.BaseRepo
import com.rageh.apiwithflow.domain.contract.AlbumsContract
import com.rageh.apiwithflow.domain.entity.Resource
import com.rageh.apiwithflow.domain.entity.Status
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRepo @Inject constructor(private val webservice: Webservice) : BaseRepo(),
    AlbumsContract {

    override fun getAlbums() = loadFromApi(webservice::getAlbums).map {
        if (it.status.get() == Status.SUCCESS) Resource.success((it.data as List<Album>).map { it.mapToEntity() })
        else it
    }

    override fun getPhotos(albumId: Long?) = loadFromApi { (webservice::getPhotos)(albumId) }.map {
        if (it.status.get() == Status.SUCCESS) Resource.success((it.data as List<Photo>).map { it.mapToEntity() })
        else it
    }

}