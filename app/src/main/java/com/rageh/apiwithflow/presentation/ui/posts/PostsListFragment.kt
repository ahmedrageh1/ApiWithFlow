package com.rageh.apiwithflow.presentation.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.databinding.FragmentPostsListBinding
import com.rageh.apiwithflow.presentation.adapter.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsListFragment : Fragment() {

    private val viewModel by viewModels<PostsViewModel>()
    private lateinit var binder: FragmentPostsListBinding
    private val adapter: PostsAdapter by lazy {
        PostsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = DataBindingUtil.inflate<FragmentPostsListBinding>(
            inflater,
            R.layout.fragment_posts_list,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.recyclerView.adapter = adapter
        observeDataChanges()
    }

    private fun observeDataChanges() {
        viewModel.postsData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

}