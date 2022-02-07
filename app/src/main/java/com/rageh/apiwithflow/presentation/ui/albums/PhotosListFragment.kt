package com.rageh.apiwithflow.presentation.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.rageh.apiwithflow.databinding.FragmentPhotosListBinding
import com.rageh.apiwithflow.domain.entity.Photo
import com.rageh.apiwithflow.domain.entity.Status
import com.rageh.apiwithflow.presentation.adapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosListFragment : Fragment() {

    private val args: PhotosListFragmentArgs by navArgs()
    private val viewModel by viewModels<AlbumsViewModel>()
    private lateinit var binding: FragmentPhotosListBinding
    private val adapter by lazy {
        PhotosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.albumId = args.albumId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPhotosListBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        observeDataChanges()
    }

    private fun observeDataChanges() {
        viewModel.photosData.observe(viewLifecycleOwner, { result ->
            when (result.status.get()) {
                Status.SUCCESS -> result.data?.let { adapter.submitList(it as List<Photo>) }
                Status.ERROR -> Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT)
                    .show()
                else -> {
                }
            }

        })
    }

}