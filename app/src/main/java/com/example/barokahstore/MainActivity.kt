package com.example.barokahstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    private var priceListAdapter = PriceListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.allPriceList.observe(this) {
            if (it.isEmpty()) {
                binding.tvEmptyAdapter.visibility = View.VISIBLE
                binding.rvDaftarHarga.visibility = View.GONE
                Log.d("cek", "masuk")
            } else {
                Log.d("cek", "kosong")
                binding.tvEmptyAdapter.visibility = View.GONE
                binding.rvDaftarHarga.visibility = View.VISIBLE

                it.let {
                    priceListAdapter.submitList(it)
                }
            }
        }
    }

    private fun initListener() {
        binding.fabNewPriceList.setOnClickListener{
            NewPriceListActivity.start(this)
        }
    }

    private fun initView() {
        viewModel.getPriceList()
        binding.rvDaftarHarga.adapter = priceListAdapter
    }


}