package com.example.kkn_unmer.module.home.viewmodel.mahasiswa

import android.content.Context
import android.net.Uri
import androidx.lifecycle.*
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaResponseModel
import com.example.kkn_unmer.network.repositories.RemoteRepository
import java.io.File

class HomeViewModel : ViewModel() {

    private val repository = RemoteRepository(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val _imageFile = MutableLiveData<File>()
    val imageFile: LiveData<File>
        get() = _imageFile

    val _photoMessage = MutableLiveData<String>()
    val photoMessage: LiveData<String>
        get() = _photoMessage

    val _photoUploaded = MutableLiveData<Boolean>()
    val photoUploaded: LiveData<Boolean>
        get() = _photoUploaded

    fun setPhotoUploaded(photoUploaded: Boolean) {
        _photoUploaded.value = photoUploaded
    }

    fun setImageFile(imageFile: File) {
        _imageFile.value = imageFile
    }

    fun setImagePath(path: String) {
        val imagePath = Uri.parse(path)
        setImageFile(File(imagePath.path!!))
    }

    fun setPhotoMessage(message: String) {
        _photoMessage.value = message
    }

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