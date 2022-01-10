package com.albumapplication.data.apiservices

import com.albumapplication.domain.models.Album
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbumList() : Response<List<Album>>
}