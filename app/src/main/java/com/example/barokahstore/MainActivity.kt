package com.example.barokahstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var rvDaftarHarga : RecyclerView
    private var list = ArrayList<HargaBarang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDaftarHarga = findViewById(R.id.rv_daftar_harga)
        rvDaftarHarga.setHasFixedSize(true)

        list.addAll(DataHarga.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvDaftarHarga.layoutManager = LinearLayoutManager(this)
        val listDaftarHargaAdapter = DaftarHargaAdapter(list)
        rvDaftarHarga.adapter = listDaftarHargaAdapter

    }


}