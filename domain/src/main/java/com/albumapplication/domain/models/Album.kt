package com.albumapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "album_table")
data class Album (
    val albumId: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int?=null,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null
): Serializable
