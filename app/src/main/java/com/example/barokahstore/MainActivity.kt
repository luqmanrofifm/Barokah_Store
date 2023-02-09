package com.example.barokahstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.barokahstore.core.ui.BottomSheetWarning
import com.example.barokahstore.core.ui.DialogCustomProgress
import com.example.barokahstore.core.utils.isNetworkAvailable
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

    override fun onResume() {
        super.onResume()
        if (isNetworkAvailable(this)){
            viewModel.synchronizeData(this)
            priceListAdapter.notifyDataSetChanged()
        }
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
                    priceListAdapter.notifyDataSetChanged()
                    priceListAdapter.
                }
            }
        }

        viewModel.loadingEvent.observe(this){
            DialogCustomProgress.toggle(supportFragmentManager, it)
        }
    }

    private fun initListener() {
        binding.fabNewPriceList.setOnClickListener{
            NewPriceListActivity.start(this, 0)
        }

        priceListAdapter.onDeletePriceListEntity ={
            BottomSheetWarning.Builder(
                supportFragmentManager,
                title = "Konfimasi",
                message = "Apakah anda yakin ingin menghapus data ini",
                positiveText = "ya",
                negativeText = "batal",
                okListener = {
                    viewModel.deletePriceList(it.id)
                }
            )
                .show()
        }

        priceListAdapter.onEditPriceListEntity = {
            NewPriceListActivity.start(this, it.id)
        }
    }

    private fun initView() {
        viewModel.getPriceList()
        binding.rvDaftarHarga.adapter = priceListAdapter
    }


}