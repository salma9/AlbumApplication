package com.albumapplication.data.repository

import com.albumapplication.data.apiservices.ApiService
import com.albumapplication.domain.models.Album
import com.albumapplication.domain.repositories.AlbumRepository
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) :AlbumRepository{

    //suspend fun getAllContents() = apiService.getAlbumList()
    override suspend fun getAlbumList(): Response<List<Album>> {
        return apiService.getAlbumList()
    }

}