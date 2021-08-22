package com.example.kkn_unmer.module.laporan.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AddLaporanResponseModel(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
) : Parcelable