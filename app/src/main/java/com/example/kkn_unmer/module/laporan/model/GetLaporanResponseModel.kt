package com.example.kkn_unmer.module.laporan.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetLaporanResponseModel(
    @SerializedName("data") val GetLaporanModel: List<GetLaporanModel>,
    @SerializedName("status") val status: Boolean
) : Parcelable