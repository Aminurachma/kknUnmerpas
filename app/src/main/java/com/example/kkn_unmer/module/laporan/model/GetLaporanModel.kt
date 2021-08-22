package com.example.kkn_unmer.module.laporan.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetLaporanModel(
    @SerializedName("Id_Laporan") val idLaporan: String,
    @SerializedName("Ket_Laporan") val ketLaporan: String,
    @SerializedName("Laporan_Created") val laporanCreated: String,
    @SerializedName("NPM_Mhs") val nPMMhs: String,
    @SerializedName("Pict_Laporan") val pictLaporan: String,
    @SerializedName("Tgl_Laporan") val tglLaporan: String
) : Parcelable