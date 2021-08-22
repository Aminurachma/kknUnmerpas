package com.example.kkn_unmer.module.mahasiswa.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.kkn_unmer.R
import com.example.kkn_unmer.base.baseview.BaseActivity
import com.example.kkn_unmer.databinding.ActivityMahasiswaBinding
import com.example.kkn_unmer.module.mahasiswa.viewmodel.MahasiswaViewModel
import com.example.kkn_unmer.utils.glide.GlideApp

class MahasiswaActivity : BaseActivity() {
    private var _binding: ActivityMahasiswaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MahasiswaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_mahasiswa)


        GlideApp.with(this)
            .load(R.drawable.unmer)
            .into(binding.logoToolbar)


        GlideApp.with(this)
            .load(R.drawable.add)
            .into(binding.imgAddMhs)

        getMahasiswa()
    }

    private fun getMahasiswa() {
        viewModel.getMahasiswa(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}