package com.rageh.apiwithflow

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.rageh.apiwithflow.di.ImageViewBindingComponentImpl
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var imageViewBindingComponent: ImageViewBindingComponentImpl
    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(imageViewBindingComponent)
    }
}