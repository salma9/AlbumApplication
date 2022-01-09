package com.example.albumapplication.interfaces

import com.albumapplication.domain.models.Album


interface ItemClickListener {

    fun onItemClickListener(album: Album)
}