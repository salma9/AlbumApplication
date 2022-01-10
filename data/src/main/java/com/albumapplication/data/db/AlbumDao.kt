package com.albumapplication.data.db

import androidx.room.*
import com.albumapplication.domain.models.Album
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbum(album: Album)

    @Query("SELECT * FROM album_table")
    fun getAllAlbums(): Flow<List<Album>>

    @Delete
    suspend fun deleteAlbum(album: Album)

    @Query("select * from album_table order by id desc")
    fun getAllAlbumsOrdered(): List<Album>

    @Query("select * from album_table WHERE id=:id ")
    fun getAlbumByID(id: Int): Album
}