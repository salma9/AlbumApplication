package com.example.albumapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albumapplication.domain.models.Album
import com.albumapplication.domain.usecases.GetAlbumsUseCase
import com.albumapplication.domain.usecases.GetLocalAlbumsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsUseCase,
                                        private val getLocalAlbumsUseCase: GetLocalAlbumsUseCase
): ViewModel() {

    private val TAG: String = MainViewModel::class.java.simpleName
    val errorMessage = MutableLiveData<String>()
    val albumList = MutableLiveData<List<Album>>()
    val remoteAlbums = arrayListOf<Album>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    /*init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getAlbums()
        }
    }*/

    suspend fun getAlbums(){
        val response = getAlbumsUseCase()
        if (response.isSuccessful) {
            Log.i(TAG, "success : " +response.message())

            response.body()?.let{
                //update lists
                albumList.postValue(it)
                remoteAlbums.clear()
                remoteAlbums.addAll(it)
                //add all albums in database
                for (album in it){
                    getLocalAlbumsUseCase.invoke(album)
                }
            }
        } else {
            onError("Error : ${response.message()} ")
            Log.e(TAG, "error : " +response.message())
        }
    }

    fun getAllAlbums() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getAlbums()
        }

    }

    private fun onError(message: String) {
        getLocalAlbumsUseCase.invoke()?.let {
            Log.i(TAG, "get list from DB")
            albumList.postValue(it)

        } ?:run {
            errorMessage.postValue(message)
        }
    }
}