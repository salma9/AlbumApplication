package com.albumapplication.domain.models

import java.io.Serializable

data class Album (
    val albumId: String? = null,
    val id: Int?=0,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null
): Serializable
