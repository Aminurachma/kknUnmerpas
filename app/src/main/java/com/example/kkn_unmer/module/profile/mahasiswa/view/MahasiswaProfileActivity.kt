package com.example.kkn_unmer.module.profile.mahasiswa.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.databinding.ActivityMahasiswaProfileBinding
import com.example.kkn_unmer.module.laporan.view.LaporanActivity
import com.example.kkn_unmer.module.profile.mahasiswa.MahasiswaProfileViewModel
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaModel
import com.example.kkn_unmer.utils.showToast

class MahasiswaProfileActivity : BaseActivity() {

    private var _binding: ActivityMahasiswaProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MahasiswaProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_mahasiswa_profile)


        getProfileMahasiswa()
        observeProfileMahasiswa()

        binding.logout.setOnClickListener {
            setLogoutDialog()
        }

        binding.riwayatlap.setOnClickListener {
            startActivity(Intent(this, LaporanActivity::class.java))
        }
    }

    private fun observeProfileMahasiswa() {
        viewModel.profileResponse.observe(this) {
            if (it.status) {
                showDataMahasiswa(it.getProfileMahasiswaModel)
            } else {
                showToast(it.message.toString())
            }
        }
    }

    private fun getProfileMahasiswa() {
        viewModel.getProfile(Prefs.NPM_Mhs, this)
    }

    private fun showDataMahasiswa(profile: GetProfileMahasiswaModel) {
        binding.apply {
            name.text = profile.namaMhs
            npm.text = profile.nPMMhs
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}