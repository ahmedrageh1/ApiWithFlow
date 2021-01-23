package com.rageh.apiwithflow.di

import android.content.Context
import com.rageh.apiwithflow.BuildConfig
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageLoadingModule {
    @Provides
    @Singleton
    fun getPicasso(@ApplicationContext context: Context, okHttpClient: OkHttpClient) =
        requireNotNull(
            Picasso.Builder(context) //add context to picasso object for enable loading images from resources and read dimens
                .downloader(OkHttp3Downloader(okHttpClient)) //attach to the same okHttpClient used in api loading with logging interceptor enabled
                .loggingEnabled(BuildConfig.DEBUG)
                .build()
        )
}