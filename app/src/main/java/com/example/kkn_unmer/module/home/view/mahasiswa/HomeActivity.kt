package com.example.kkn_unmer.module.home.view.mahasiswa

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.databinding.ActivityHomeBinding
import com.example.kkn_unmer.module.profile.mahasiswa.model.GetProfileMahasiswaModel
import com.example.kkn_unmer.module.home.viewmodel.mahasiswa.HomeViewModel
import com.example.kkn_unmer.module.laporan.view.AddLaporanActivity
import com.example.kkn_unmer.module.profile.mahasiswa.view.MahasiswaProfileActivity
import com.example.kkn_unmer.utils.glide.GlideApp
import com.example.kkn_unmer.utils.showToast
import com.example.kkn_unmer.utils.timeStampFormat
import org.joda.time.LocalDate

class HomeActivity : BaseActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private val REQUEST_PERMISSION_STORAGE_CODE = 5733

    private lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        GlideApp.with(this)
            .load(R.drawable.unmer)
            .into(binding.logoToolbar)

        setupClickListeners()
        getProfileMahasiswa()
        observeProfileMahasiswa()

        binding.profilePictures.setOnClickListener {
            startActivity(Intent(this, MahasiswaProfileActivity::class.java))
        }

    }

    private fun setupClickListeners() {
        binding.btnLaporan.setOnClickListener {
            //imagePicker(imagePickerLauncher)
            val currentDate = LocalDate.now().toString(timeStampFormat)
            if (Prefs.Tgl_Laporan == currentDate && Prefs.isHaveUploadedReport) {
                showToast("Anda sudah upload laporan hari ini!") // nanti kamu pindahin ke file string nanti aku mau tulis ke textview yang gone yang bsia yaa
                // kamu bisa tapi? iyaaa nanti aku cobain hehee
                // yaudah nanti toastnya dihapus trus kamu munculin yang kamu gone itu pake .toVisible
//                binding.cardLaporan.toVisible() // contoh kalo mau nampilin
//                binding.cardLaporan.toGone() // kalo mau ngilangin okeee yang jangan di hapus ini
            } else {
                Prefs.isHaveUploadedReport = false
                startActivity(Intent(this, AddLaporanActivity::class.java))
            }
        }
    }

//    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        val resultCode = result.resultCode
//        val data = result.data
//        if (resultCode == Activity.RESULT_OK) {
//            val fileUri = data?.data!!
//            val path = fileUri.path ?: ""
//            viewModel.setImagePath(path)
//
//        }
//    }

    private fun getProfileMahasiswa() {
        viewModel.getProfile(Prefs.NPM_Mhs, this)
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

    private fun showDataMahasiswa(profile: GetProfileMahasiswaModel) {
        binding.apply {
            name.text = profile.namaMhs
            fakultas.text = profile.fakultasMhs
            npm.text = profile.nPMMhs
        }
    }

//    private fun checkLaporanStatus() {
//        when (Prefs.laporanStatus) {
//            0 -> {
//                binding.btnLaporan.visibility = visible()
//                binding.ket.visibility = gone()
//
//            }
//            1 -> {
//                binding.btnLaporan.visibility = visible()
//                binding.ket.visibility = gone()
//            }
//            2 -> {
//                binding.btnLaporan.visibility = visible()
//                binding.ket.visibility = gone()
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}