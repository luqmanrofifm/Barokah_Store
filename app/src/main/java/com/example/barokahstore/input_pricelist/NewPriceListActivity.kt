package com.example.barokahstore.input_pricelist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.barokahstore.R
import com.example.barokahstore.databinding.ActivityMainBinding
import com.example.barokahstore.databinding.ActivityNewPriceListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPriceListActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityNewPriceListBinding

    private val viewModel: NewPriceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
            viewModel.addPriceList()
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

        fun start(context: Context?) {
            val intent = Intent(context, NewPriceListActivity::class.java)
            context?.startActivity(intent)
        }
    }
}