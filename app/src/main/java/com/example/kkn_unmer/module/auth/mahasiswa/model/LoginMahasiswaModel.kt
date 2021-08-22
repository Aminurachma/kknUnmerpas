package com.example.kkn_unmer.module.auth.mahasiswa.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
@Parcelize
data class LoginMahasiswaModel(
    @SerializedName("Alamat_Mhs") val alamatMhs: String,
    @SerializedName("Fakultas_Mhs") val fakultasMhs: String,
    @SerializedName("Mahasiswa_Created") val mahasiswaCreated: String,
    @SerializedName("NPM_Mhs") val nPMMhs: String,
    @SerializedName("Nama_Mhs") val namaMhs: String,
    @SerializedName("token") val token: String
) : Parcelable