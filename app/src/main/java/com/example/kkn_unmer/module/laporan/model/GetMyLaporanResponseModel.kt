package com.example.kkn_unmer.module.laporan.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetMyLaporanResponseModel(
    @SerializedName("data") val GetMyLaporanModel: List<GetMyLaporanModel>,
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?
) : Parcelable