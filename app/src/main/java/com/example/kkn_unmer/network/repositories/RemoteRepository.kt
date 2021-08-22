package com.example.kkn_unmer.network.repositories

import android.content.Context
import com.example.kkn_unmer.base.basemodel.BaseResponseModel
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.module.auth.mahasiswa.model.LoginMahasiswaResponseModel
import com.example.kkn_unmer.module.laporan.model.GetMyLaporanResponseModel
import com.example.kkn_unmer.module.mahasiswa.model.DeleteMahasiswaResponseModel
import com.example.kkn_unmer.module.mahasiswa.model.GetMahasiswaResponseModel
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaResponseModel
import com.example.kkn_unmer.network.Network
import com.example.kkn_unmer.network.ServiceFactory
import kotlinx.coroutines.CoroutineScope
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class RemoteRepository(coroutineScope: CoroutineScope) {

    private val network = Network(coroutineScope)
    private val restApi = ServiceFactory.create()


//Auth
    // Login Mahasiswa
    fun postLogin(NPM_Mhs: String, context: Context,
                  onSuccess: (LoginMahasiswaResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.loginMahasiswa(NPM_Mhs)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

//    fun postLoginAdmin(username: String, password: String, context: Context,
//                       onSuccess: (LoginAdminResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postAdminLogin(username, password)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }



    //Mahasiswa
    fun postAddMhs(nPMMhs: String, namaMhs: String, fakultasMhs: String, alamatMhs: String,
                   context: Context, onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.postAddMhs(nPMMhs , namaMhs, fakultasMhs, alamatMhs)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

    fun getMahasiswa(context: Context, onSuccess: (GetMahasiswaResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.getMahasiswa()
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

    fun getProfile(nPMMhs: String, context: Context, onSuccess: (GetProfileMahasiswaResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.getProfile(nPMMhs)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

    fun postUpdateMhs(nPMMhs: String, namaMhs: String, fakultasMhs: String, alamatMhs: String, context: Context,
                          onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.postUpdateMhs(nPMMhs,namaMhs, fakultasMhs, alamatMhs)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

    fun deleteMhs(NPM_Mhs: String, context: Context, onSuccess: (DeleteMahasiswaResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.postDeleteMhs(NPM_Mhs)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }


    //Admin

//    fun getAdmin(context: Context, onSuccess: (GetAdminResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getAdmin()
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }

//    fun postAddAdmin(idAdmin: String, namaAdmin: String, usernameAdmin: String, passwordAdmin: String,
//                     emailAdmin: String, phoneAdmin: String, jenisKelaminAdmin: String,
//                   context: Context, onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postAddAdmin(idAdmin , namaAdmin, usernameAdmin, passwordAdmin, emailAdmin, phoneAdmin,jenisKelaminAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun deleteAdmin(idAdmin: String, context: Context, onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postDeleteAdmin(idAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }

    // Laporan
    fun addLaporan(pictLaporan: File, tglLaporan: String, ketLaporan: String, context: Context, onSuccess: (BaseResponseModel) -> Unit,
                   onFinally: (Boolean) -> Unit) {
        val requestBody = pictLaporan.asRequestBody("multipart/form-file".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("Pict_Laporan", pictLaporan.name, requestBody)
        val npm = Prefs.NPM_Mhs.toRequestBody("text/plain".toMediaTypeOrNull())
        val date = tglLaporan.toRequestBody("text/plain".toMediaTypeOrNull())
        val catatan = ketLaporan.toRequestBody("text/plain".toMediaTypeOrNull())
        network.request(context, {
            restApi.postAddLaporan(image, npm, date, catatan)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

    fun getMyLaporan(nPMMhs: String, context: Context, onSuccess: (GetMyLaporanResponseModel) -> Unit,
                     onFinally:(Boolean) -> Unit) {
        network.request(context, {
            restApi.getMyLaporan(nPMMhs)
        }, {
            onSuccess(it!!)
        }, {
            onFinally(true)
        })
    }

//    fun postLogin(username: String, password: String, context: Context,
//                  onSuccess: (LoginResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.loginKaryawan(username, password)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postLoginAdmin(username: String, password: String, context: Context,
//                       onSuccess: (LoginAdminResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postAdminLogin(username, password)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//

//
//    fun postAddAdmin(idAdmin: String, namaAdmin: String, usernameAdmin: String, passwordAdmin: String, emailAdmin: String, genderAdmin: String,
//                      birthdayLocAdmin: String, birthdayDateAdmin: String, roleAdmin: String, addressAdmin: String, phoneAdmin: String,
//                        context: Context, onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.addAdmin(idAdmin, namaAdmin, usernameAdmin, passwordAdmin, emailAdmin, genderAdmin, birthdayLocAdmin,
//                birthdayDateAdmin, roleAdmin, addressAdmin, phoneAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdatePassword(idKaryawan: String, currentPassword: String, newPassword: String, context: Context,
//                           onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postUpdatePassword(idKaryawan, currentPassword, newPassword)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun getProfile(idKaryawan: String, context: Context, onSuccess: (GetProfileResponseModel) -> Unit,
//                   onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getProfile(idKaryawan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun getMyAbsensi(idKaryawan: String, context: Context, onSuccess: (GetMyAbsensiResponseModel) -> Unit,
//                   onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getMyAbsensi(idKaryawan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdateProfile(nama: String, username: String, email: String, phone: String,
//                          gender: String, address: String, idJabatan: String, birthdayLoc: String, birthdayDate: String, idKaryawan: String, context: Context,
//                          onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postUpdateProfile(idKaryawan, nama, username, email, phone, gender, address, idJabatan, birthdayLoc, birthdayDate)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdateProfileAdmin(idAdmin: String, namaAdmin: String, usernameAdmin: String, emailAdmin: String,
//                          genderAdmin: String, addressAdmin: String, birthdayLocAdmin: String, birthdayDateAdmin: String, phoneAdmin: String,  context: Context,
//                          onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postUpdateProfileAdmin(idAdmin, namaAdmin, usernameAdmin, emailAdmin, genderAdmin, addressAdmin, birthdayLocAdmin, birthdayDateAdmin, phoneAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun getProfileAdmin(idAdmin: String, context: Context, onSuccess: (GetProfileAdminResponseModel) -> Unit,
//                   onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getProfileAdmin(idAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdatePhotoAdmin(photo: File, context: Context, onSuccess: (BaseResponseModel) -> Unit,
//                        onFinally: (Boolean) -> Unit) {
//        val requestBody = photo.asRequestBody("multipart/form-file".toMediaTypeOrNull())
//        val image = MultipartBody.Part.createFormData("Avatar_Admin", photo.name, requestBody)
//        val idAdmin = Prefs.idAdmin.toRequestBody("text/plain".toMediaTypeOrNull())
//        network.request(context, {
//            restApi.postUpdatePhotoAdmin(image, idAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdatePhoto(photo: File, context: Context, onSuccess: (BaseResponseModel) -> Unit,
//                        onFinally: (Boolean) -> Unit) {
//        val requestBody = photo.asRequestBody("multipart/form-file".toMediaTypeOrNull())
//        val image = MultipartBody.Part.createFormData("Avatar", photo.name, requestBody)
//        val idKaryawan = Prefs.idKaryawan.toRequestBody("text/plain".toMediaTypeOrNull())
//        network.request(context, {
//            restApi.postUpdatePhoto(image, idKaryawan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//
//    fun postUpdatePasswordAdmin(idAdmin: String, currentPassword: String, newPassword: String, context: Context,
//                                onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postUpdatePasswordAdmin(idAdmin, currentPassword, newPassword)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postAddJabatan(idJabatan: String, namaJabatan: String, context: Context,
//                         onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.addJabatan(idJabatan, namaJabatan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdateJabatan( idJabatan: String, namaJabatan: String,context: Context,
//                          onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postUpdateJabatan(idJabatan,namaJabatan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postUpdateKaryawanStatus( idKaryawan: String, status: Int,context: Context,
//    onSuccess: (UpdateKaryawanResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postUpdateKaryawanStatus(idKaryawan, status)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postDeleteJabatan(idJabatan: String , context: Context, onSuccess: (DeleteJabatanResponseModel) -> Unit,
//                            onFinally: (Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postDeleteJabatan(idJabatan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postDeleteAdmin(idAdmin: String , context: Context, onSuccess: (DeleteAdminResponseModel) -> Unit,
//                          onFinally: (Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postDeleteAdmin(idAdmin)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//
//    fun getAbsensi(context: Context, onSuccess: (GetAbsensiResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getAbsensi()
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun getJabatan(context: Context, onSuccess: (GetJabatanResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getJabatan()
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//    fun getAdmin(context: Context, onSuccess: (GetAdminResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getAdmin()
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun deleteKaryawan(idKaryawan: String, context: Context, onSuccess: (BaseResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.postDeleteKaryawan(idKaryawan)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postAbsenMasuk(idKaryawan: String, idJabatan: String, nama: String, tanggalAbsen: String, jamDatang: String, LongtitudeDatang: String, LatitudeDatang: String,
//                        context: Context, onSuccess: (AddAbsensiResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.absenMasuk(idKaryawan, idJabatan, nama, tanggalAbsen,jamDatang, LongtitudeDatang, LatitudeDatang)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }
//
//    fun postAbsenPulang(idAbsensi: String, jamPulang: String, longtitudePulang: String, latitudePulang: String,
//                        context: Context, onSuccess: (UpdateAbsensiResponseModel) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.absenPulang(idAbsensi, jamPulang, longtitudePulang, latitudePulang)
//        }, {
//            onSuccess(it!!)
//        }, {
//            onFinally(true)
//        })
//    }

}