package com.rageh.apiwithflow.presentation.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        return FragmentAlbumsListBinding.inflate(
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

    override fun onItemClicked(item: Album) {
        findNavController().navigate(
            AlbumsListFragmentDirections.actionAlbumsListFragmentToPhotosListFragment(
                item.id
            )
        )
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