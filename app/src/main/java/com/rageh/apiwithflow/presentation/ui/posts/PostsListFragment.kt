package com.rageh.apiwithflow.presentation.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.data.api.entity.Status
import com.rageh.apiwithflow.data.entity.Post
import com.rageh.apiwithflow.databinding.FragmentPostsListBinding
import com.rageh.apiwithflow.presentation.adapter.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsListFragment : Fragment() {

    private val viewModel by viewModels<PostsViewModel>()
    private lateinit var binding: FragmentPostsListBinding
    private val adapter by lazy {
        PostsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentPostsListBinding>(
            inflater,
            R.layout.fragment_posts_list,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        observeDataChanges()
    }

    private fun observeDataChanges() {
        viewModel.postsData.observe(viewLifecycleOwner, { result ->
            when (result.status.get()) {
                Status.SUCCESS -> result.data?.let { adapter.submitList(it as List<Post>) }
                Status.ERROR -> Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT)
                    .show()
                else -> {
                }
            }

        })
    }

}