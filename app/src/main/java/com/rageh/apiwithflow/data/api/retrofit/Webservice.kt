package com.rageh.apiwithflow.data.api.retrofit

import com.rageh.apiwithflow.data.entity.Album
import com.rageh.apiwithflow.data.entity.Photo
import com.rageh.apiwithflow.data.entity.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {

    @GET("posts")
    fun getPosts(): Flow<List<Post>>

    @GET("albums")
    fun getAlbums(): Flow<List<Album>>

//    @GET("photos")
//    fun getPhotos(): Flow<List<Photo>>

    @GET("photos")
    fun getPhotos(@Query("albumId") albumId: Long?): Flow<List<Photo>>
}