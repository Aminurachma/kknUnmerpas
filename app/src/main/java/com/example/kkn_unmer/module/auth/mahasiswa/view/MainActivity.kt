package com.example.kkn_unmer.module.auth.mahasiswa.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.databinding.ActivityMainBinding
import com.example.kkn_unmer.module.auth.mahasiswa.model.LoginMahasiswaModel
import com.example.kkn_unmer.module.auth.mahasiswa.viewmodel.MainViewModel
import com.example.kkn_unmer.module.home.view.mahasiswa.HomeActivity
import com.example.kkn_unmer.utils.showToast

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupProgressBar()
        setupClickListeners()
        observeLoginResponse()

    }


    private fun observeLoginResponse() {
        viewModel.loginResponse.observe(this) {
            if (it.status) {
                saveToPrefs(it.loginMahasiswaModel)
                startActivity(Intent(this, HomeActivity::class.java))
                super.finish()
            } else {
                showToast(getString(R.string.npm_failed))
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            when {
                binding.npm.text.isNullOrEmpty() -> binding.npm.error =
                    getString(R.string.empty_username)
                else -> viewModel.loginPost(binding.npm.text.toString(), this)
            }
        }
    }


    private fun saveToPrefs(loginMahasiswaModel: LoginMahasiswaModel) {
        Prefs.NPM_Mhs = loginMahasiswaModel.nPMMhs
        Prefs.Nama_Mhs = loginMahasiswaModel.namaMhs
        Prefs.Fakultas_Mhs = loginMahasiswaModel.fakultasMhs
        Prefs.Alamat_Mhs = loginMahasiswaModel.alamatMhs
        Prefs.token = loginMahasiswaModel.token

    }

    private fun setupProgressBar() {
        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progress.show()
            } else {
                progress.dismiss()
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}