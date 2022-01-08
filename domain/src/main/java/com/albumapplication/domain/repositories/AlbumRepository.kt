package com.albumapplication.domain.repositories

import com.albumapplication.domain.models.Album
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface AlbumRepository {

    suspend fun getAlbumList(): Response<List<Album>>
}