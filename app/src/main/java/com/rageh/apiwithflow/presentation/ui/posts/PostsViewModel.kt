package com.rageh.apiwithflow.presentation.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.domain.PostsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val handler: PostsHandler) : ViewModel() {

    val postsData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emitSource(handler.getPosts().asLiveData(Dispatchers.IO))
    }

}