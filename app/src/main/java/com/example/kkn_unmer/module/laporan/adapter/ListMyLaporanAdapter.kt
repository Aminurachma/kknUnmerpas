package com.example.kkn_unmer.module.laporan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kkn_unmer.BuildConfig
import com.example.kkn_unmer.data.Prefs
import com.example.kkn_unmer.databinding.ItemLaporanBinding
import com.example.kkn_unmer.module.laporan.model.GetMyLaporanModel
import com.example.kkn_unmer.utils.*
import java.text.SimpleDateFormat

class ListMyLaporanAdapter(val context: Context, private val onClickListener: OnClickListener) :
    ListAdapter<GetMyLaporanModel, ListMyLaporanAdapter.MyLaporanViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLaporanViewHolder =
        MyLaporanViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyLaporanViewHolder, position: Int) {
        val getMyLaporanModel = getItem(position)
        holder.bind(getMyLaporanModel)

        val date = getDateTimeFromString(getMyLaporanModel.laporanCreated)
        val fixedDate = date.minusHours(1)
        val inputDateFormat = SimpleDateFormat(Constants.CURRENT_DATE_FORMAT).parse(
            fixedDate.toString(
                dateTimeHoursFormat
            )
        )
        val createdAt = DateHelper.getTimeAgo(inputDateFormat.time)

        holder.binding.creationDate.text = createdAt

        holder.binding.mhsName.text = Prefs.Nama_Mhs


        logDebug("waktuprefs: $date")
        Glide.with(holder.binding.laporanPict.context).load(BuildConfig.BASE_IMG_URL+ getMyLaporanModel.pictLaporan)
            .into(holder.binding.laporanPict)

    }


    class MyLaporanViewHolder(val binding: ItemLaporanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(getMyLaporanModel: GetMyLaporanModel) {
            binding.itemMyLaporan = getMyLaporanModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyLaporanViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLaporanBinding.inflate(layoutInflater, parent, false)
                return MyLaporanViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (getMyLaporanModel: GetMyLaporanModel) -> Unit) {
        fun onClick(getMyLaporanModel: GetMyLaporanModel) = clickListener(getMyLaporanModel)
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<GetMyLaporanModel>() {

        override fun areItemsTheSame(
            oldItem: GetMyLaporanModel,
            newItem: GetMyLaporanModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: GetMyLaporanModel,
            newItem: GetMyLaporanModel
        ): Boolean {
            return oldItem.idLaporan == newItem.idLaporan
        }

    }
}