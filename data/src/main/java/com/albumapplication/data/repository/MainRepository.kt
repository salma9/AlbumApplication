package com.albumapplication.data.repository

import com.albumapplication.data.apiservices.ApiService
import com.albumapplication.data.db.AlbumDao
import com.albumapplication.domain.models.Album
import com.albumapplication.domain.repositories.AlbumRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService,
                                            private val albumDao: AlbumDao) :AlbumRepository{

    //suspend fun getAllContents() = apiService.getAlbumList()
    override suspend fun getRemoteAlbumList(): Response<List<Album>> {
        return apiService.getAlbumList()
    }

    override fun getLocalAlbumList(): List<Album> {
        return albumDao.getAllAlbumsOrdered()
    }

    override suspend fun insertAlbum(album: Album) {
        albumDao.saveAlbum(album)
    }

}