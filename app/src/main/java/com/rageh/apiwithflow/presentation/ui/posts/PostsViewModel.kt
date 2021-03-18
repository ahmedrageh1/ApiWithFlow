package com.rageh.apiwithflow.presentation.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rageh.apiwithflow.data.api.entity.Resource
import com.rageh.apiwithflow.domain.PostsHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val handler: PostsHandler) : ViewModel() {

    val postsData = flow {
        emit(Resource.loading(null))
        emitAll(handler.getPosts())
    }.asLiveData()

}