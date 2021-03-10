package com.rageh.apiwithflow.presentation.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.domain.AlbumsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val handler: AlbumsHandler) :
    ViewModel() {

    var albumId = 0L

    val albumsData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emitSource(handler.getAlbums().asLiveData(Dispatchers.IO))
    }

    val photosData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emitSource(handler.getPhotos(albumId).asLiveData(Dispatchers.IO))
    }

}