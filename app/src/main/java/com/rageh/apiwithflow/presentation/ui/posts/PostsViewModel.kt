package com.rageh.apiwithflow.presentation.ui.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.domain.PostsHandler
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.Dispatchers

@FragmentScoped
class PostsViewModel @ViewModelInject constructor(private val handler: PostsHandler) : ViewModel() {

    val postsData = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        emitSource(handler.getPosts().asLiveData(Dispatchers.IO))
    }

}