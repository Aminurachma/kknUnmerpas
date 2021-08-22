package com.example.kkn_unmer.module.profile.mahasiswa.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetProfileMahasiswaResponseModel(
    @SerializedName("data") val getProfileMahasiswaModel: GetProfileMahasiswaModel,
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
) : Parcelable