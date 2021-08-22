package com.example.kkn_unmer.module.mahasiswa.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@Parcelize
data class GetMahasiswaResponseModel(
    @SerializedName("data") val `data`: List<GetMahasiswaModel>,
    @SerializedName("status") val status: Boolean
) : Parcelable