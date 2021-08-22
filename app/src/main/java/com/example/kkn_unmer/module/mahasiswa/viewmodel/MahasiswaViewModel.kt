package com.example.kkn_unmer.module.mahasiswa.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kkn_unmer.module.mahasiswa.model.GetMahasiswaResponseModel
import com.example.kkn_unmer.network.repositories.RemoteRepository

class MahasiswaViewModel: ViewModel() {
    private val repository = RemoteRepository(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _addAllMhsResponse = MutableLiveData<GetMahasiswaResponseModel>()
    val addAllMhsResponse: LiveData<GetMahasiswaResponseModel> = _addAllMhsResponse

    fun getMahasiswa(context: Context) {
        _isLoading.value = true
        repository.getMahasiswa(context, {
            _addAllMhsResponse.postValue(it)
        }, {
            _isLoading.postValue(false)
        })
    }
}