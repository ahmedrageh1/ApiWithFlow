package com.rageh.apiwithflow.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rageh.apiwithflow.BR
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.databinding.ItemPostBinding
import com.rageh.apiwithflow.domain.entity.Post
import com.rageh.apiwithflow.presentation.base.BaseSimpleBindingAdapter


class PostsAdapter :
    BaseSimpleBindingAdapter<Post, ItemPostBinding>(object :
        DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem.toString() == newItem.toString()

    }) {

    override fun getItemLayout(viewType: Int) = R.layout.item_post

    override fun onBindViewHolder(holder: ViewHolder<ItemPostBinding>, position: Int) {
        holder.viewBinding.setVariable(BR.post, getItem(position))
        holder.viewBinding.executePendingBindings()
    }

}