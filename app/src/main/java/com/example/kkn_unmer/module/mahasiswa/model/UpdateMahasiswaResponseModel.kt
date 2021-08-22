package com.example.kkn_unmer.module.mahasiswa.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UpdateMahasiswaResponseModel(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
) : Parcelable