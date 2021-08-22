package com.example.kkn_unmer.module.laporan.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kkn_unmer.base.basemodel.BaseResponseModel
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.network.repositories.RemoteRepository
import com.example.kkn_unmer.utils.dateTimeFormat
import com.example.kkn_unmer.utils.logDebug
import org.joda.time.LocalDate
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddLaporanViewModel : ViewModel() {
    private val repository = RemoteRepository(viewModelScope)

    val _namaMhs = MutableLiveData<String>()
    val namaMhs: LiveData<String>
        get() = _namaMhs

    val _npmMhs = MutableLiveData<String>()
    val npmMhs: LiveData<String>
        get() = _npmMhs

    val _tgl = MutableLiveData<String>()
    val tgl: LiveData<String>
        get() = _tgl

    val _ket = MutableLiveData<String>()
    val ket: LiveData<String>
        get() = _ket

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun setStatus(status: Boolean) {
        _status.value = status
    }

    fun setKet(ket: String) {
        _ket.value = ket
    }


    init {
        val dateFormatter = SimpleDateFormat(dateTimeFormat)
        val now = Date()
        _namaMhs.value = Prefs.Nama_Mhs
        _npmMhs.value = Prefs.NPM_Mhs
        _tgl.value = dateFormatter.format(now)
    }


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

    val _addLaporanResponse = MutableLiveData<BaseResponseModel>()
    val addLaporanResponse: LiveData<BaseResponseModel> = _addLaporanResponse


    fun uploadImage(ket: String, context: Context) {
        val currentDate = LocalDate().toString(dateTimeFormat)
        logDebug("checkDate: $currentDate")
        setLoading(true)
        val repository = RemoteRepository(viewModelScope)
        repository.addLaporan(imageFile.value!!, currentDate, ket, context, {
            _addLaporanResponse.postValue(it)
            Prefs.laporanStatus = 1
        }, {
            setLoading(false)
        })
    }
}