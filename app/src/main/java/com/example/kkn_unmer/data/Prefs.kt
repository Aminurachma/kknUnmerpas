package com.example.kkn_unmer.data

import com.chibatching.kotpref.KotprefModel

object Prefs : KotprefModel() {

    // penamaan variable menggunakan camelCase

    var NPM_Mhs by stringPref() // npmMhs
    var Nama_Mhs by stringPref() // namaMhs
    var Fakultas_Mhs by stringPref() // fakultasMhs
    var Alamat_Mhs by stringPref() // alamatMhs

    //    var status by stringPref()
    var token by stringPref()

    var laporanStatus by intPref(0)
    var Id_Laporan by stringPref()
    var Ket_Laporan by stringPref()
    var Pict_Laporan by nullableStringPref()
    var Tgl_Laporan by stringPref()
    var isHaveUploadedReport by booleanPref(false)
    //var tanggalAbsen by stringPref()
    //var jamPulang by stringPref()
    //var idAbsensi by stringPref()

    var idAdmin by stringPref()
    var usernameAdmin by stringPref()
    var namaAdmin by stringPref()
    var phoneAdmin by stringPref()
    var emailAdmin by stringPref()
    var jenisKelaminAdmin by stringPref()
    var tokenAdmin by stringPref()


    //var PREFERENCE_LATITUDE by nullableStringPref()
    //var PREFERENCE_LONGTITUDE by nullableStringPref()
}