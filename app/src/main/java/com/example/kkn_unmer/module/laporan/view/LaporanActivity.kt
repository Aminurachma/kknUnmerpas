package com.example.kkn_unmer.module.laporan.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.databinding.ActivityLaporanBinding
import com.example.kkn_unmer.module.laporan.adapter.ListMyLaporanAdapter
import com.example.kkn_unmer.module.laporan.viewmodel.LaporanViewModel
import com.example.kkn_unmer.module.profile.mahasiswa.view.MahasiswaProfileActivity
import com.example.kkn_unmer.utils.glide.GlideApp
import com.example.kkn_unmer.utils.showToast

class LaporanActivity : BaseActivity() {

    private var _binding: ActivityLaporanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LaporanViewModel by viewModels()
    private lateinit var adapter: ListMyLaporanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_laporan)

        GlideApp.with(this)
            .load(R.drawable.unmer)
            .into(binding.logoToolbar)

        setupProgressBar()
        setupSwipeRefreshLayout()


        binding.profilePicture.setOnClickListener {
            startActivity(Intent(this, MahasiswaProfileActivity::class.java))
        }

        viewModel.getMyLaporan(Prefs.NPM_Mhs, this)
        // call view
//        binding.rvLaporan

        viewModel.getMyLaporanResponse.observe(this) {
            if (it.status) {
                val data = it.GetMyLaporanModel
                if (data.isNotEmpty()) {
                    adapter =
                        ListMyLaporanAdapter(
                            this,
                            ListMyLaporanAdapter.OnClickListener { myLaporan ->
//                    val activity = ActivityJabatanBinding()
//                    activity.setA(allJabatan)
//                    sheet.show(supportFragmentManager, "SheetAdminFragment")
                            })
                    adapter.submitList(data)
                    adapter.notifyDataSetChanged()
                    binding.rvLaporan.setHasFixedSize(true)
                    binding.rvLaporan.adapter = adapter
                }
            } else {
                showToast(it.message.toString())
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyLaporan(Prefs.NPM_Mhs, this)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

//    private fun observeGetLaporan() {
//        viewModel.getMyLaporanResponse.observe(this) {
//            if (it.status) {
//                showDataMahasiswa(it.getProfileMahasiswaModel)
//            } else {
//                showToast(it.message.toString())
//            }
//        }
//    }

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