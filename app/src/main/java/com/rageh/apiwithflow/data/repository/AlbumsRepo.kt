package com.rageh.apiwithflow.data.repository

import com.rageh.apiwithflow.data.api.retrofit.Webservice
import com.rageh.apiwithflow.data.repository.base.BaseRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumsRepo @Inject constructor(private val webservice: Webservice) : BaseRepo() {

    fun getAlbums() = loadFromApi(webservice::getAlbums)

    fun getPhotos(albumId: Long? = null) = loadFromApi { (webservice::getPhotos)(albumId) }


}