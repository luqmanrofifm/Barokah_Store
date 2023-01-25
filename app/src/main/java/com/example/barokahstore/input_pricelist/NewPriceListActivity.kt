package com.example.barokahstore.input_pricelist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.barokahstore.databinding.ActivityNewPriceListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPriceListActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityNewPriceListBinding

    private val viewModel: NewPriceListViewModel by viewModels()

    private val id by lazy {
        intent.getIntExtra(EXTRA_ID, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (id != 0) {
            viewModel.getDetailLocalPriceList(id)
            binding.btnCreateNewPriceList.text = "Ubah Harga Barang"

            viewModel.getLocalDataEvent.observe(this) {
                binding.edtNamaBarang.setText(it.nama)
                binding.edtHargaBarang.setText(it.harga.toString())
                binding.edtKeterangan.setText(it.keterangan)
            }
        }

        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        viewModel.successSaveEvent.observe(this){
            if (it){
                this.finish()
            }
        }

        viewModel.isAllFilledEvent.observe(this){
            binding.btnCreateNewPriceList.isEnabled = it
        }
    }

    private fun initListener() {
        binding.btnCreateNewPriceList.setOnClickListener {
            if (id == 0){
                viewModel.addPriceList()
            } else {
                viewModel.updatePriceList(id)
            }
        }
    }

    private fun initView() {
        observeNextButton()
    }

    private fun observeNextButton() {
        with(binding){
            edtNamaBarang.doAfterTextChanged {
                if (it.toString().isNotEmpty()) {
                    viewModel.setNamaBarang(it.toString())
                    binding.edtNamaBarang.error = null
                }
                else {
                    viewModel.setNamaBarang("")
                    binding.edtNamaBarang.error = "Nama barang harus diisi"
                }
            }

            edtHargaBarang.doAfterTextChanged {
                if (it.toString().isNotEmpty()) {
                    try {
                        viewModel.setHargaBarang(it.toString().toInt())
                        binding.edtHargaBarang.error = null
                    } catch (e: Exception){
                        binding.edtHargaBarang.error = "Harga barang harus berupa angka"
                    }

                }
                else {
                    viewModel.setHargaBarang(0)

                }
            }

            edtKeterangan.doAfterTextChanged {
                if (it.toString().isNotEmpty()) {
                    viewModel.setKeterangan(it.toString())
                    binding.edtKeterangan.error = null
                }
                else {
                    viewModel.setKeterangan("")
                }
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        fun start(context: Context?, id: Int) {
            val intent = Intent(context, NewPriceListActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context?.startActivity(intent)
        }
    }
}