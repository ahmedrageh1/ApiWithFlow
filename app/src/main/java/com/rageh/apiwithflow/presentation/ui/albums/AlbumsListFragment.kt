package com.rageh.apiwithflow.presentation.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.data.api.entity.Status
import com.rageh.apiwithflow.data.entity.Album
import com.rageh.apiwithflow.databinding.FragmentAlbumsListBinding
import com.rageh.apiwithflow.presentation.adapter.AlbumsAdapter
import com.rageh.apiwithflow.presentation.base.BaseSimpleBindingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsListFragment : Fragment(), BaseSimpleBindingAdapter.OnItemClickListener<Album> {

    private val viewModel by viewModels<AlbumsViewModel>()
    private lateinit var binding: FragmentAlbumsListBinding
    private val adapter by lazy {
        AlbumsAdapter(this@AlbumsListFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentAlbumsListBinding>(
            inflater,
            R.layout.fragment_albums_list,
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

    override fun onItemClicked(item: Album) {
        findNavController().navigate(R.id.photosListFragment, bundleOf("albumId" to item.id))
    }

    private fun observeDataChanges() {
        viewModel.albumsData.observe(viewLifecycleOwner, { result ->
            when (result.status.get()) {
                Status.SUCCESS -> result.data?.let { adapter.submitList(it as List<Album>) }
                Status.ERROR -> Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT)
                    .show()
                else -> {
                }
            }

        })
    }
}