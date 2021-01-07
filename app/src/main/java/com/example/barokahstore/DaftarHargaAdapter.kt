package com.example.barokahstore

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

class DaftarHargaAdapter (private val listHarga: ArrayList<HargaBarang>) : RecyclerView.Adapter<DaftarHargaAdapter.ListViewHolder>(){
    private lateinit var onItemCallback : OnItemClickCallback

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemCallback =onItemCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardview_daftarharga, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val harga_barang = listHarga[position]

        holder.tvJudul.text = harga_barang.judul
        holder.tvHarga.text = harga_barang.harga
        holder.tvKeterangan.text= harga_barang.keterangan

        holder.itemView.setOnClickListener { onItemCallback.onItemClicked(listHarga[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listHarga.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvJudul : TextView = itemView.findViewById(R.id.tv_judul)
        var tvHarga :TextView = itemView.findViewById(R.id.tv_harga)
        var tvKeterangan :TextView = itemView.findViewById(R.id.tv_keterangan)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: HargaBarang)
    }
}
