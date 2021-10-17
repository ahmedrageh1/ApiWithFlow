package com.rageh.apiwithflow.presentation.ui.albums

import androidx.lifecycle.*
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.data.entity.Album
import com.rageh.apiwithflow.domain.AlbumsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val handler: AlbumsHandler) :
    ViewModel() {

    private val _albumsLiveData by lazy { MutableLiveData<Resource<List<Album>>>().also { loadAlbums() } }
    val albumsData = _albumsLiveData as LiveData<Resource<List<Album>>>

    var albumId = 0L

    fun loadAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            _albumsLiveData.postValue(Resource.loading())
            handler.getAlbums().first().also { _albumsLiveData.postValue( it as Resource<List<Album>> )}
        }
    }

    val photosData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emitSource(handler.getPhotos(albumId).asLiveData(Dispatchers.IO))
    }

}