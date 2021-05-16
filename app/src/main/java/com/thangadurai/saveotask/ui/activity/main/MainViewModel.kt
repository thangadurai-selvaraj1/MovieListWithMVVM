package com.thangadurai.saveotask.ui.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponse
import com.thangadurai.saveotask.data.network.ApiUtils
import com.thangadurai.saveotask.data.network.Resource
import com.thangadurai.saveotask.data.network.Status
import com.thangadurai.saveotask.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _apiResponseAlbum = MutableLiveData<Resource<BasePhotoResponse>>(null)
    val apiResponseAlbum: MutableLiveData<Resource<BasePhotoResponse>> = _apiResponseAlbum


    fun getPhotosId() {

        viewModelScope.launch(Dispatchers.IO) {
            _apiResponseAlbum.postValue(Resource(Status.LOADING, null, null))
            kotlin.runCatching {
                authRepository.getPhotosId()
            }.onSuccess { success ->
                success.collect {
                    _apiResponseAlbum.postValue(it)
                }
            }.onFailure {
                print(it.message)
            }
        }

    }
}