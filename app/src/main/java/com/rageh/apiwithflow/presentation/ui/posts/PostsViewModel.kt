package com.rageh.apiwithflow.presentation.ui.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.rageh.apiwithflow.domain.PostsHandler
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class PostsViewModel @ViewModelInject constructor(private val handler: PostsHandler) : ViewModel()