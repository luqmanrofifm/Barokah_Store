package com.example.barokahstore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.databinding.ItemCardviewDaftarhargaBinding

class PriceListAdapter: ListAdapter<PriceListEntity, PriceListAdapter.PriceListActvityViewHolder>(
    PriceListActivityDiffCallback()
) {
    var onDeletePriceListEntity: ((PriceListEntity) -> Unit)? = null
    var onEditPriceListEntity: ((PriceListEntity) -> Unit)? = null

    inner class PriceListActvityViewHolder(private val binding: ItemCardviewDaftarhargaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PriceListEntity){
            with(binding){
                tvJudul.text = item.nama
                tvHarga.text = item.harga.toString()
                tvKeterangan.text = item.keterangan
                tvSatuan.text = item.satuan

                btnDeleteItem.setOnClickListener {
                    onDeletePriceListEntity?.invoke(item)
                }

                btnEditItem.setOnClickListener {
                    onEditPriceListEntity?.invoke(item)
                }
            }
        }
    }

    class PriceListActivityDiffCallback: DiffUtil.ItemCallback<PriceListEntity>() {
        override fun areItemsTheSame(oldItem: PriceListEntity, newItem: PriceListEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PriceListEntity,
            newItem: PriceListEntity
        ): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.harga == newItem.harga
                    && oldItem.nama == newItem.nama
                    && oldItem.satuan == newItem.satuan
                    && oldItem.keterangan == newItem.keterangan
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceListActvityViewHolder {
        val view =ItemCardviewDaftarhargaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceListActvityViewHolder(view)
    }

    override fun onBindViewHolder(holder: PriceListActvityViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
}