package com.barokahstore.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.barokahstore.app.core.ui.BottomSheetWarning
import com.barokahstore.app.core.ui.DialogCustomProgress
import com.barokahstore.app.core.utils.isNetworkAvailable
import com.barokahstore.app.databinding.ActivityMainBinding
import com.barokahstore.app.input_pricelist.NewPriceListActivity
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
            Log.d("cek", "resume terus")
            viewModel.synchronizeData(this, priceListAdapter)
        }
    }

    private fun initObserver() {
        viewModel.allPriceList.observe(this) {

            if (it.isEmpty()) {
                binding.tvEmptyAdapter.visibility = View.VISIBLE
                binding.rvDaftarHarga.visibility = View.GONE
            } else {
                binding.tvEmptyAdapter.visibility = View.GONE
                binding.rvDaftarHarga.visibility = View.VISIBLE

                it.let {
                    priceListAdapter.submitList(it)
                    priceListAdapter.notifyDataSetChanged()
                }
            }
        }

        viewModel.loadingEvent.observe(this){
            DialogCustomProgress.toggle(supportFragmentManager, it)
        }
    }

    private fun initListener() {
        binding.btnCreateNewPriceList.setOnClickListener{
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

        binding.btnSearch.setOnClickListener {
            viewModel.searchDataPriceList(binding.edtNama.text.toString())
            viewModel.allPriceList.observe(this) {
                if (it.isEmpty()) {
                    binding.tvEmptyAdapter.visibility = View.VISIBLE
                    binding.rvDaftarHarga.visibility = View.GONE
                } else {
                    binding.tvEmptyAdapter.visibility = View.GONE
                    binding.rvDaftarHarga.visibility = View.VISIBLE

                    it.let {
                        priceListAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun initView() {
        viewModel.getPriceList()
        binding.rvDaftarHarga.adapter = priceListAdapter
        binding.edtNama.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                viewModel.setWordSearch(it.toString())
            }
            else {
                viewModel.getPriceList()
                viewModel.allPriceList.observe(this) {
                    if (it.isEmpty()) {
                        binding.tvEmptyAdapter.visibility = View.VISIBLE
                        binding.rvDaftarHarga.visibility = View.GONE
                    } else {
                        binding.tvEmptyAdapter.visibility = View.GONE
                        binding.rvDaftarHarga.visibility = View.VISIBLE

                        it.let {
                            priceListAdapter.submitList(it)
                        }
                    }
                }
            }
        }
    }


}