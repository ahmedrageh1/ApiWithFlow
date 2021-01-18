package com.rageh.apiwithflow.presentation.ui.posts

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rageh.apiwithflow.domain.PostsHandler
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@FragmentScoped
class PostsViewModel @ViewModelInject constructor(private val handler: PostsHandler) : ViewModel() {

    val postsData = liveData(Dispatchers.IO) {
        handler.getPosts().catch { cause ->
            Log.e("Error", "error in loading posts api", cause)
        }.collect {
            emit(it)
        }
    }

}