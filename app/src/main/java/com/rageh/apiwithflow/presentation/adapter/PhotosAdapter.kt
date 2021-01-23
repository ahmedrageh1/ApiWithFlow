package com.rageh.apiwithflow.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rageh.apiwithflow.BR
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.data.entity.Photo
import com.rageh.apiwithflow.databinding.ItemPhotoBinding
import com.rageh.apiwithflow.presentation.base.BaseSimpleBindingAdapter


class PhotosAdapter :
    BaseSimpleBindingAdapter<Photo, ItemPhotoBinding>(object :
        DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.toString() == newItem.toString()

    }) {

    override fun getItemLayout(viewType: Int) = R.layout.item_photo

    override fun onBindViewHolder(holder: ViewHolder<ItemPhotoBinding>, position: Int) {
        holder.viewBinding.setVariable(BR.photo, getItem(position))
        holder.viewBinding.executePendingBindings()
    }

}