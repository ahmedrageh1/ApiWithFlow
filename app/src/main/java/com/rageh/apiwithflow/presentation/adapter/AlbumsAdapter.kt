package com.rageh.apiwithflow.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rageh.apiwithflow.BR
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.data.entity.Album
import com.rageh.apiwithflow.databinding.ItemAlbumBinding
import com.rageh.apiwithflow.presentation.base.BaseSimpleBindingAdapter


class AlbumsAdapter :
    BaseSimpleBindingAdapter<Album, ItemAlbumBinding>(object :
        DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Album, newItem: Album) =
            oldItem.toString() == newItem.toString()

    }) {

    override fun getItemLayout(viewType: Int) = R.layout.item_album

    override fun onBindViewHolder(holder: ViewHolder<ItemAlbumBinding>, position: Int) {
        holder.viewBinding.setVariable(BR.album, getItem(position))
        holder.viewBinding.executePendingBindings()
    }

}