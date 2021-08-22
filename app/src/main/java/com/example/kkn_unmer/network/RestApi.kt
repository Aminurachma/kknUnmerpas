package com.example.kkn_unmer.network

import com.example.kkn_unmer.base.basemodel.BaseResponseModel
import com.example.kkn_unmer.module.auth.mahasiswa.model.LoginMahasiswaResponseModel
import com.example.kkn_unmer.module.laporan.model.GetMyLaporanResponseModel
import com.example.kkn_unmer.module.mahasiswa.model.DeleteMahasiswaResponseModel
import com.example.kkn_unmer.module.mahasiswa.model.GetMahasiswaResponseModel
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface RestApi {

    //Auth
    //Login Mahasiswa
    @FormUrlEncoded
    @POST("mahasiswa/login")
    suspend fun loginMahasiswa(@Field("NPM_Mhs") nPMMhs: String): Response<LoginMahasiswaResponseModel>
//
//    @FormUrlEncoded
//    @POST("admin/login")
//    suspend fun postAdminLogin(
//        @Field("Username_Admin") usernameAdmin: String,
//        @Field("Password_Admin") passwordAdmin: String
//    ): Response<LoginAdminResponseModel>

    // Mahasiswa
    @GET("mahasiswa")
    suspend fun getMahasiswa(): Response<GetMahasiswaResponseModel>

    @GET("mahasiswa")
    suspend fun getProfile(@Query("NPM_Mhs") npmMhs: String): Response<GetProfileMahasiswaResponseModel>

    @FormUrlEncoded
    @POST("mahasiswa/add")
    suspend fun postAddMhs(
        @Field("NPM_Mhs") nPMMhs: String,
        @Field("Nama_Mhs") namaMhs: String,
        @Field("Fakultas_Mhs") fakultasMhs: String,
        @Field("Alamat_Mhs") alamatMhs: String
    ): Response<BaseResponseModel>

    @FormUrlEncoded
    @POST("mahasiswa/update")
    suspend fun postUpdateMhs(
        @Field("NPM_Mhs") nPMMhs: String,
        @Field("Nama_Mhs") namaMhs: String,
        @Field("Fakultas_Mhs") fakultasMhs: String,
        @Field("Alamat_Mhs") alamatMhs: String
    ): Response<BaseResponseModel>

    @FormUrlEncoded
    @POST("mahasiswa/delete")
    suspend fun postDeleteMhs(@Field("NPM_Mhs") nPMMhs: String): Response<DeleteMahasiswaResponseModel>

//Admin
//
//
//    @GET("admin")
//    suspend fun getAdmin(): Response<GetAdminResponseModel>
//
//    @FormUrlEncoded
//    @POST("admin/add")
//    suspend fun postAddAdmin(
//        @Field("Id_Admin") idAdmin: String,
//        @Field("Nama_Admin") namaAdmin: String,
//        @Field("Username_Admin") usernameAdmin: String,
//        @Field("Phone_Admin") phoneAdmin: String,
//        @Field("JenisKelamin_Admin") jenisKelaminAdmin: String,
//        @Field("Email_Admin") emailAdmin: String,
//        @Field("Password_Admin") alamatMhs: String
//    ): Response<BaseResponseModel>
//
//    @FormUrlEncoded
//    @POST("admin/delete")
//    suspend fun postDeleteAdmin(@Field("Id_Admin") nPMMhs: String): Response<BaseResponseModel>

    //Laporan
    @Multipart
    @POST("laporan/add")
    suspend fun postAddLaporan(
        @Part pictLaporan: MultipartBody.Part,
        @Part("NPM_Mhs") npm: RequestBody,
        @Part("Tgl_Laporan") tglLaporan: RequestBody,
        @Part("Ket_Laporan") ketLaporan: RequestBody): Response<BaseResponseModel>

    @GET("laporan")
    suspend fun getMyLaporan(@Query("NPM_Mhs") nPMMhs: String): Response<GetMyLaporanResponseModel>



//    @FormUrlEncoded
//    @POST("laporan/add")
//    suspend fun postAddLaporan(@Field("Id_Laporan") idLaporan: String,
//                           @Field("NPM_Mhs") nPMMhs: String,
//                           @Field("Pict_Laporan") pictLaporan: String,
//                           @Field("Tgl_Laporan") tglLaporan: String,
//                           @Field("Ket_Laporan") ketLaporan: String
//    ) : Response<BaseResponseModel>

//    @Multipart
//    @POST("transaksi/uploadBuktiTransfer")
//    suspend fun postUploadBuktiTransfer(@Part buktiTransfer: MultipartBody.Part,
//                                        @Part("id_transaksi") transaksiId: RequestBody,
//                                        @Part("id_rekening") rekeningId: RequestBody): Response<BaseResponseModel>


    // AUTH

//    @FormUrlEncoded
//    @POST("karyawan/login")
//    suspend fun loginKaryawan(@Field("Username") username: String,
//                              @Field("Password") password: String): Response<LoginResponseModel>
//
//    @FormUrlEncoded
//    @POST("admin/login")
//    suspend fun postAdminLogin(@Field("Username_Admin") usernameAdmin: String,
//                               @Field("Password_Admin") passwordAdmin: String) : Response<LoginAdminResponseModel>
//
//  // MAHASISWA
//
//    @GET("mahasiswa")
//    suspend fun getProfile(@Query("Npm_Mhs") nPMMhs: String): Response<GetMahasiswaResponseModel>
//


//    @FormUrlEncoded
//    @POST("karyawan/changepassword")
//    suspend fun postUpdatePassword(@Field("Id_Karyawan") idKaryawan: String,
//                                   @Field("currentPassword") currentPassword: String,
//                                   @Field("Password") password: String) : Response<BaseResponseModel>
//
//
//    @FormUrlEncoded
//    @POST("karyawan/update")
//    suspend fun postUpdateProfile(@Field("Id_Karyawan") idKaryawan: String,
//                                  @Field("Nama") nama: String,
//                                  @Field("Username") username: String,
//                                  @Field("Email") email: String,
//                                  @Field("Phone") phone: String,
//                                  @Field("JenisKelamin") gender: String,
//                                  @Field("Alamat") address: String,
//                                  @Field("Id_Jabatan") idJabatan: String,
//                                  @Field("TempatLahir") birthdayLoc: String,
//                                  @Field("TanggalLahir") birthdayDate: String) : Response<BaseResponseModel>
//    @Multipart
//    @POST("karyawan/updateavatar")
//    suspend fun postUpdatePhoto(@Part photo: MultipartBody.Part,
//                                @Part("Id_Karyawan") idKaryawan: RequestBody) : Response<BaseResponseModel>
//
//

//    @FormUrlEncoded
//    @POST("karyawan/delete")
//    suspend fun postDeleteKaryawan(@Field("Id_Karyawan") idKaryawan: String) : Response<BaseResponseModel>
//
//    @FormUrlEncoded
//    @POST("karyawan/updatestatus")
//    suspend fun postUpdateKaryawanStatus(@Field("Id_Karyawan") idKaryawan: String,
//                                         @Field("status") status: Int) : Response<UpdateKaryawanResponseModel>
//
//
//    // ADMIN
//
//    @FormUrlEncoded
//    @POST("admin/add")
//    suspend fun addAdmin(@Field("Id_Admin") idAdmin: String,
//                         @Field("Nama_Admin") namaAdmin: String,
//                         @Field( "Username_Admin") usernameAdmin: String,
//                         @Field( "Password_Admin") passwordAdmin: String,
//                         @Field("Email_Admin") emailAdmin: String,
//                         @Field("JenisKelamin_Admin") genderAdmin: String,
//                         @Field("TempatLahir_Admin") birthdayLocAdmin: String,
//                         @Field("TanggalLahir_Admin") birthdayDateAdmin: String,
//                         @Field("Role_Admin") roleAdmin: String,
//                         @Field("Alamat_Admin") addressAdmin: String,
//                         @Field("Phone_Admin") phoneAdmin: String) : Response<BaseResponseModel>
//
//    @GET("admin")
//    suspend fun getProfileAdmin(@Query("Id_Admin") idAdmin: String): Response<GetProfileAdminResponseModel>
//
//
//    @FormUrlEncoded
//    @POST("admin/update")
//    suspend fun postUpdateProfileAdmin(@Field("Id_Admin") idAdmin: String,
//                                       @Field("Nama_Admin") namaAdmin: String,
//                                       @Field("Username_Admin") usernameAdmin: String,
//                                       @Field("Email_Admin") emailAdmin: String,
//                                       @Field("JenisKelamin_Admin") genderAdmin: String,
//                                       @Field("Alamat_Admin") addressAdmin: String,
//                                       @Field("TempatLahir_Admin") birthdayLocAdmin: String,
//                                       @Field("TanggalLahir_Admin") birthdayDateAdmin: String,
//                                       @Field("Phone_Admin") phoneAdmin: String) : Response<BaseResponseModel>
//
//    @FormUrlEncoded
//    @POST("admin/changepassword")
//    suspend fun postUpdatePasswordAdmin(@Field("Id_Admin") idAdmin: String,
//                                        @Field("currentPassword") currentPassword: String,
//                                        @Field("Password_Admin") passwordAdmin: String) : Response<BaseResponseModel>
//    @Multipart
//    @POST("admin/updatephoto")
//    suspend fun postUpdatePhotoAdmin(@Part photo: MultipartBody.Part,
//                                     @Part("Id_Admin") idAdmin: RequestBody) : Response<BaseResponseModel>
//
//    @FormUrlEncoded
//    @POST("admin/delete")
//    suspend fun postDeleteAdmin(@Field("Id_Admin") idAdmin: String): Response<DeleteAdminResponseModel>
//
//
//    @FormUrlEncoded
//    @POST("jabatan/add")
//    suspend fun addJabatan(@Field("Id_Jabatan") idJabatan: String,
//                            @Field("Nama_Jabatan") namaJabatan: String) : Response<BaseResponseModel>
//
//    @FormUrlEncoded
//    @POST("jabatan/update")
//    suspend fun postUpdateJabatan(@Field("Id_Jabatan") idJabatan: String,
//                                  @Field("Nama_Jabatan") namaJabatan: String) : Response<BaseResponseModel>
//
//    @FormUrlEncoded
//    @POST("jabatan/delete")
//    suspend fun postDeleteJabatan(@Field("Id_Jabatan") idJabatan: String): Response<DeleteJabatanResponseModel>
//
//
//    @GET("absensi")
//    suspend fun getAbsensi(): Response<GetAbsensiResponseModel>
//
//    @GET("absensi")
//    suspend fun getMyAbsensi(@Query("Id_Karyawan") idKaryawan: String): Response<GetMyAbsensiResponseModel>
//
//
//    @FormUrlEncoded
//    @POST("absensi/add")
//    suspend fun absenMasuk(@Field("Id_Karyawan") idKaryawan: String,
//                           @Field("Id_Jabatan") idJabatan: String,
//                           @Field("Nama") nama: String,
//                           @Field("Tanggal_Absen") tanggalAbsen: String,
//                           @Field("Jam_Datang") jamDatang: String,
//                           @Field("Longtitude_Datang") longtitudeDatang: String,
//                           @Field("Latitude_Datang") latitudeDatang: String) : Response<AddAbsensiResponseModel>
//
//    @FormUrlEncoded
//    @POST("absensi/update")
//    suspend fun absenPulang(@Field("Id_Absensi") idAbsensi: String,
//                            @Field("Jam_Pulang") jamPulang: String,
//                            @Field("Longtitude_Pulang") longtitudePulang: String,
//                            @Field("Latitude_Pulang") latitudePulang: String) : Response<UpdateAbsensiResponseModel>
//
//
////    @GET("jabatan")
////    suspend fun getJabatan(@Query("Id_Jabatan") idJabatan: String): Response<GetJabatanResponseModel>
//
//    @GET("admin")
//    suspend fun getAdmin(): Response<GetAdminResponseModel>
//
//    @GET("jabatan")
//    suspend fun getJabatan(): Response<GetJabatanResponseModel>
//
//    @GET("absensi")
//    suspend fun getAbsensiByIdKaryawan(@Query("Id_Karyawan") idKaryawan: String): Response<GetProfileResponseModel>

}