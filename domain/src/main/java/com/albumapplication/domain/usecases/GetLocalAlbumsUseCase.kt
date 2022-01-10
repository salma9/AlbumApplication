package com.albumapplication.domain.usecases

import com.albumapplication.domain.models.Album
import com.albumapplication.domain.repositories.AlbumRepository
import javax.inject.Inject

class GetLocalAlbumsUseCase @Inject constructor(private val albumRepository: AlbumRepository) {

    operator fun invoke() = albumRepository.getLocalAlbumList()

    suspend operator fun invoke(album: Album) = albumRepository.insertAlbum(album)
}