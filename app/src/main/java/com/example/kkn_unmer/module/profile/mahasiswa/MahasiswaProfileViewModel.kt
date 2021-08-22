package com.example.kkn_unmer.module.profile.mahasiswa

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaResponseModel
import com.example.kkn_unmer.network.repositories.RemoteRepository

class MahasiswaProfileViewModel : ViewModel() {

    private val repository = RemoteRepository(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _profileResponse = MutableLiveData<GetProfileMahasiswaResponseModel>()
    val profileResponse: LiveData<GetProfileMahasiswaResponseModel> = _profileResponse

    fun getProfile(nPMMhs: String, context: Context) {
        _isLoading.value = true
        repository.getProfile(nPMMhs, context, {
            _profileResponse.postValue(it)
        }, {
            _isLoading.postValue(false)
        })
    }
}