package com.example.kkn_unmer.module.laporan.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kkn_unmer.module.laporan.model.GetMyLaporanModel
import com.example.kkn_unmer.module.laporan.model.GetMyLaporanResponseModel
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaResponseModel
import com.example.kkn_unmer.network.repositories.RemoteRepository

class LaporanViewModel: ViewModel() {

    private val repository = RemoteRepository(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _getMyLaporanResponse = MutableLiveData<GetMyLaporanResponseModel>()
    val getMyLaporanResponse: LiveData<GetMyLaporanResponseModel> = _getMyLaporanResponse

    fun getMyLaporan(nPMMhs: String, context: Context) {
        _isLoading.value = true
        repository.getMyLaporan(nPMMhs, context, {
            _getMyLaporanResponse.postValue(it)
        }, {
            _isLoading.postValue(false)
        })
    }
}