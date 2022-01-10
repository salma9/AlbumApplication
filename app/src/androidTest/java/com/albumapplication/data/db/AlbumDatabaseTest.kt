package com.albumapplication.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.albumapplication.domain.models.Album
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
internal class AlbumDatabaseTest {

    private lateinit var albumDao: AlbumDao
    private lateinit var db: AlbumDatabase
    val album = Album(20,20,
        "albumTest",
        "https://via.placeholder.com/600/627b42",
        "https://via.placeholder.com/150/627b42")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AlbumDatabase::class.java).build()
        albumDao = db.albumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndFindAlbumByID() {

        runBlocking{
            albumDao.saveAlbum(album)
            val albumResult = albumDao.getAlbumByID(20)
            assertThat(albumResult, equalTo(album))
        }
    }

    @Test
    fun writeAndFindAlbumInList() {

        runBlocking{
            albumDao.saveAlbum(album)
            val albumResult = albumDao.getAllAlbumsOrdered()
            assertThat(albumResult.contains(album), equalTo(true))
        }
    }
}