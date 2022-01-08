package com.example.albumapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albumapplication.domain.models.Album
import com.albumapplication.domain.usecases.GetAlbumsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsUseCase): ViewModel() {

    private val TAG: String = MainViewModel::class.java.simpleName
    val errorMessage = MutableLiveData<String>()
    val albumList = MutableLiveData<List<Album>>()
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
            albumList.postValue(response.body())
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
        errorMessage.postValue(message)
    }
}