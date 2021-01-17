package com.rageh.apiwithflow.di

import com.chenxyu.retrofit.adapter.FlowCallAdapterFactory
import com.rageh.apiwithflow.data.api.Webservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun httpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Inject
    @Provides
    @Singleton
    fun getOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Inject
    @Provides
    @Singleton
    fun getRetrofit(gsonConverterFactory: GsonConverterFactory, client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .client(client).build()

    @Inject
    @Provides
    @Singleton
    fun getWebservice(retrofit: Retrofit) =
        retrofit.create(Webservice::class.java)


    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}