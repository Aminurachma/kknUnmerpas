package com.example.kkn_unmer.module.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.module.auth.mahasiswa.view.MainActivity

class SplashScreenActivity : BaseActivity() {

    private val REQUEST_LOCATION_CODE = 1234

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT: Long = 3000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            //getCurrentLocation()
            nextActivity()
        }, SPLASH_TIME_OUT)
    }

    private fun nextActivity() {
        startActivity(loginIntent(this))
        finish()
    }

    private fun loginIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
        finish()
    }

//    private fun getCurrentLocation() {
//        if (checkPermissionForLocation(this, REQUEST_LOCATION_CODE)) {
//            val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                buildAlertMessageNoGps()
//            } else {
//                nextActivity()
//            }
//        }
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            REQUEST_LOCATION_CODE -> {
//                if (grantResults.isNotEmpty() && grantResults[0] ==
//                    PackageManager.PERMISSION_GRANTED
//                ) {
//                    //permission from popup granted
//                    getCurrentLocation()
//                } else {
//                    //permission from popup denied
//                    showToast("Permission Denied")
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == 11) {
//            getCurrentLocation()
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }
}
