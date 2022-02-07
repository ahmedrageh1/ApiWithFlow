package com.rageh.apiwithflow.data.api.retrofit

import com.rageh.apiwithflow.data.model.Album
import com.rageh.apiwithflow.data.model.Photo
import com.rageh.apiwithflow.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: Long?): List<Photo>
}