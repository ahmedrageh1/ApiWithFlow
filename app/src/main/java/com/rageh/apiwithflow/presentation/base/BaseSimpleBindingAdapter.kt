package com.rageh.apiwithflow.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSimpleBindingAdapter<T, VH : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseSimpleBindingAdapter.ViewHolder<VH>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VH> {
        val itemBinding = DataBindingUtil.inflate<VH>(
            LayoutInflater.from(parent.context),
            getItemLayout(viewType), parent, false
        )
        return ViewHolder(itemBinding)
    }

    @LayoutRes
    abstract fun getItemLayout(viewType: Int): Int

    class ViewHolder<VH : ViewDataBinding>(val viewBinding: VH) :
        RecyclerView.ViewHolder(viewBinding.root)

    interface OnItemClickListener<T> {
        fun onItemClicked(item: T)
    }

}