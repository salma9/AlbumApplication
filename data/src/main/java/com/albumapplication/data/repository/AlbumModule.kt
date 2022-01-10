package com.albumapplication.data.repository

import android.content.Context
import com.albumapplication.data.apiservices.ApiClientModule
import com.albumapplication.data.apiservices.ApiService
import com.albumapplication.data.db.AlbumDao
import com.albumapplication.data.db.AlbumDatabase
import com.albumapplication.domain.repositories.AlbumRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AlbumModule {
    @Provides
    @Singleton
    fun providesAlbumRepository(apiService: ApiService, albumDao: AlbumDao): AlbumRepository {
        return MainRepository(apiService, albumDao)
    }

    @Provides
    @Singleton
    fun provideAlbumDao(context: Context): AlbumDao {
        return AlbumDatabase.getDatabase(context).albumDao()
    }
}