package com.rageh.apiwithflow.presentation.ui.albums

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.domain.AlbumsHandler
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.Dispatchers

@FragmentScoped
class AlbumsViewModel @ViewModelInject constructor(private val handler: AlbumsHandler) :
    ViewModel() {

    val albumsData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emitSource(handler.getAlbums().asLiveData(Dispatchers.IO))
    }

}