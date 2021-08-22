package com.example.kkn_unmer.module.auth.mahasiswa.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.module.auth.mahasiswa.model.LoginMahasiswaModel
import com.example.kkn_unmer.module.auth.mahasiswa.model.LoginMahasiswaResponseModel
import com.example.kkn_unmer.network.repositories.RemoteRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    val _loginResponse = MutableLiveData<LoginMahasiswaResponseModel>()
    val loginResponse: LiveData<LoginMahasiswaResponseModel> = _loginResponse

    fun loginPost(npmMhs: String, context: Context) {
        setLoading(true)
        val repository = RemoteRepository(viewModelScope)
        repository.postLogin(npmMhs, context, {
            _loginResponse.postValue(it)
        }, {
            setLoading(false)
        })
    }

}