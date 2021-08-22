package com.example.kkn_unmer.module.profile.mahasiswa.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetProfileMahasiswaModel(
    @SerializedName("Alamat_Mhs") val alamatMhs: String,
    @SerializedName("Fakultas_Mhs") val fakultasMhs: String,
    @SerializedName("Mahasiswa_Created") val mahasiswaCreated: String,
    @SerializedName("NPM_Mhs") val nPMMhs: String,
    @SerializedName("Nama_Mhs") val namaMhs: String
) : Parcelable