package com.aminurachma.e_absensiunmerpas.base.baseview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kkn_unmer.widget.AppProgressDialog

abstract class BaseFragment: Fragment() {

    lateinit var progress : AppProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
    }

    protected fun setupProgress() {
        progress = AppProgressDialog(requireContext())
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

//    protected fun setLogoutDialog() {
//        val alert = AlertDialog.Builder(requireContext())
//        alert.setMessage(getString(R.string.exit_dialogue))
//        alert.setPositiveButton(android.R.string.yes) { _, _ ->
//            clearData()
//            startActivity(Intent(context, LoginActivity::class.java))
//            requireActivity().finish()
//        }
//        alert.setNegativeButton(android.R.string.no) { dialog, _ ->
//            dialog.cancel()
//        }
//        alert.show()
//    }

//    private fun clearData() {
//        Prefs.userId = ""
//        Prefs.name = ""
//        Prefs.userName = ""
//        Prefs.email = ""
//        Prefs.address = ""
//        Prefs.phone = ""
//        Prefs.gender = ""
//        Prefs.birthday = ""
//        Prefs.photo = ""
//        Prefs.token = ""
//        Prefs.adminId = ""
//        Prefs.admintType = ""
//    }

}