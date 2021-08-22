package com.example.kkn_unmer.module.laporan.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.databinding.ActivityAddLaporanBinding
import com.example.kkn_unmer.module.home.view.mahasiswa.HomeActivity
import com.example.kkn_unmer.module.laporan.viewmodel.AddLaporanViewModel
import com.example.kkn_unmer.utils.imagePicker
import com.example.kkn_unmer.utils.showToast
import com.example.kkn_unmer.utils.timeStampFormat
import com.example.kkn_unmer.utils.toVisible
import org.joda.time.LocalDate

class AddLaporanActivity : BaseActivity()  {

    private var _binding: ActivityAddLaporanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddLaporanViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_add_laporan)

        setupClickListeners()
        showDataMahasiswa()
        observeAddLaporanResponse()

        setupProgressBar()

    }

    private fun setupClickListeners() {
        binding.btnChange.setOnClickListener {
            imagePicker(imagePickerLauncher)
        }

        binding.btnLaporan.setOnClickListener {
            when {
                binding.npm.text.toString().isEmpty() -> binding.npm.error =
                    getString(R.string.empty_field)
                binding.ket.text.toString().isEmpty() -> binding.ket.error =
                    getString(R.string.empty_field)
//                binding.tgl.text.toString().isEmpty() -> binding.tgl.error =
//                    getString(R.string.empty_field)
                else -> viewModel.uploadImage(binding.ket.text.toString(),this)
            }
        }

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
    private fun showDataMahasiswa() {
        binding.apply {
            nama.text = Prefs.Nama_Mhs
            npm.text = Prefs.NPM_Mhs
        }
    }

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val resultCode = result.resultCode
        val data = result.data
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data!!
            val path = fileUri.path ?: ""
            binding.imgLaporan.setImageURI(fileUri)
            viewModel.setImagePath(path)
            binding.imgLaporan.toVisible()
//            viewModel.uploadImage(this)

        }
    }

    // gak perlu buat gini, langsung aja panggil intent
   // private fun uploadIntent(context: Context): Intent {
     //   startActivity(Intent(this, AddLaporanActivity::class.java))
   // }

    private fun observeAddLaporanResponse() {
        viewModel.addLaporanResponse.observe(this) {
            if (it.status) {
                Prefs.Tgl_Laporan = LocalDate.now().toString(timeStampFormat)
                Prefs.isHaveUploadedReport = true
                showToast(getString(R.string.berhasilupload))
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                showToast(getString(R.string.login_failed))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}