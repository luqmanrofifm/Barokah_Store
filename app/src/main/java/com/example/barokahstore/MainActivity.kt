package com.example.barokahstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barokahstore.databinding.ActivityMainBinding
import com.example.barokahstore.input_pricelist.NewPriceListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

//    private lateinit var rvDaftarHarga : RecyclerView
    private var list = ArrayList<HargaBarang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        rvDaftarHarga = findViewById(R.id.rv_daftar_harga)
//        rvDaftarHarga.setHasFixedSize(true)
        binding.rvDaftarHarga.setHasFixedSize(true)

        list.addAll(DataHarga.listData)
        showRecyclerList()

        binding.fabNewPriceList.setOnClickListener{
            NewPriceListActivity.start(this)
        }
    }

    private fun showRecyclerList() {
        binding.rvDaftarHarga.layoutManager = LinearLayoutManager(this)
        val listDaftarHargaAdapter = DaftarHargaAdapter(list)
        binding.rvDaftarHarga.adapter = listDaftarHargaAdapter

    }


}