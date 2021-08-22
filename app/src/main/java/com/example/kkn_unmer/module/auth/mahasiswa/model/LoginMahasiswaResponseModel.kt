package com.example.kkn_unmer.module.auth.mahasiswa.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@Parcelize
data class LoginMahasiswaResponseModel(
    @SerializedName("data") val loginMahasiswaModel: LoginMahasiswaModel,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
) : Parcelable