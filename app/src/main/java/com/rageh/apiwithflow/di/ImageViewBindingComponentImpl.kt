package com.rageh.apiwithflow.di

import androidx.databinding.DataBindingComponent
import com.rageh.apiwithflow.util.binding.ImageViewBindingAdapters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageViewBindingComponentImpl @Inject constructor(private val imageViewBindingAdapters: ImageViewBindingAdapters) :
    DataBindingComponent {
    //works as provider module for adapters with non static methods (if you need to inject objects like picasso)
    override fun getImageViewBindingAdapters() = imageViewBindingAdapters

}