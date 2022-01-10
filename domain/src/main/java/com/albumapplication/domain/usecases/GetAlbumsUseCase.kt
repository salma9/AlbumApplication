package com.albumapplication.domain.usecases

import com.albumapplication.domain.repositories.AlbumRepository
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(private val albumRepository: AlbumRepository) {

    suspend operator fun invoke() = albumRepository.getRemoteAlbumList()
}