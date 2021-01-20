package com.rageh.apiwithflow.data.api.retrofit

import com.rageh.apiwithflow.data.entity.Album
import com.rageh.apiwithflow.data.entity.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Webservice {

    @GET("posts")
    fun getPosts(): Flow<List<Post>>

    @GET("albums")
    fun getAlbums(): Flow<List<Album>>

    @GET("photos")
    fun getPhotos(): Flow<List<Post>>
}