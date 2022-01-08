package com.albumapplication.data.repository

import com.albumapplication.data.apiservices.ApiService
import com.albumapplication.domain.repositories.AlbumRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AlbumModule {
    @Provides
    @Singleton
    fun providesAlbumRepository(apiService: ApiService): AlbumRepository {
        return MainRepository(apiService)
    }
}