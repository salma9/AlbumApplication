package com.albumapplication.domain.repositories

import com.albumapplication.domain.models.Album
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface AlbumRepository {

    suspend fun getRemoteAlbumList(): Response<List<Album>>

    fun getLocalAlbumList(): List<Album>?

    suspend fun insertAlbum(album: Album)
}