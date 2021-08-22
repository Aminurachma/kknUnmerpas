package com.example.kkn_unmer.base.baseview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.kkn_unmer.R
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.module.auth.mahasiswa.view.MainActivity
import com.example.kkn_unmer.module.splashscreen.SplashScreenActivity
import com.example.kkn_unmer.widget.AppProgressDialog
import com.google.android.material.button.MaterialButton

@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {

    lateinit var progress: AppProgressDialog

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
    }

    protected fun setupProgress() {
        progress = AppProgressDialog(this)
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

    protected fun setLogoutDialog() {
        val alert = AlertDialog.Builder(this)
        alert.setMessage(getString(R.string.exit_dialogue))
        alert.setPositiveButton(android.R.string.yes) { _, _ ->
            clearData()
           val i = (Intent(this, MainActivity::class.java))
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }
        alert.setNegativeButton(android.R.string.no) { dialog, _ ->
            dialog.cancel()
        }   0
        alert.show()
    }


    private fun clearData() {

        Prefs.NPM_Mhs = ""
        Prefs.Nama_Mhs = ""
        Prefs.Alamat_Mhs = ""
        Prefs.Fakultas_Mhs = ""
        Prefs.Tgl_Laporan = ""


        Prefs.idAdmin = ""
        Prefs.usernameAdmin = ""
        Prefs.namaAdmin = ""
        Prefs.phoneAdmin = ""
        Prefs.emailAdmin = ""
        Prefs.jenisKelaminAdmin = ""
        Prefs.tokenAdmin = ""
    }

    protected fun clearLaporan() {

        //Prefs.jamDatang  = ""
        //Prefs.tanggalAbsen  = ""
        //Prefs.jamPulang= ""
        // Prefs.absenStatus = 0

        var laporanStatus = 0
        var Ket_Laporan = ""
        var Pict_Laporan = ""
        var Tgl_Laporan = ""

    }

//    fun submitButtonDisable(button: MaterialButton) {
//        button.isEnabled = false
//        button.isClickable = false
//        button.backgroundTintList = ContextCompat.getColorStateList(button.context, R.color.greyText)
//    }

    fun submitButtonEnable(button: MaterialButton) {
        button.isEnabled = true
        button.isClickable = true
        button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.orange2)
    }

//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return
//        }
//
//        this.doubleBackToExitPressedOnce = true
//        showToast(resources.getString(R.string.exit_dialog))
//
//        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
//    }

}