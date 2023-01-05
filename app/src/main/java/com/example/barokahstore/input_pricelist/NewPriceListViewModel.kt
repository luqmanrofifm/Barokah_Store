package com.example.barokahstore.input_pricelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.domain.usecase.AddPriceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NewPriceListViewModel @Inject constructor(
    private val addPriceListUseCase: AddPriceListUseCase
) : ViewModel()  {
    val isAllFilledEvent = MutableLiveData<Boolean>()
    val successSaveEvent = MutableLiveData<Boolean>()

    private var namaBarang = ""
    private var hargaBarang = 0
    private var keterangan = ""

    init {
        isAllFilledEvent.value = false
        successSaveEvent.value = false
    }
    private fun toggleNextButton() {
        isAllFilledEvent.value = namaBarang.isNotEmpty() && hargaBarang.toString() != "0"
    }

    fun setNamaBarang(name: String) {
        namaBarang = name
        toggleNextButton()
    }

    fun setHargaBarang(harga: Int) {
        hargaBarang = harga
        toggleNextButton()
    }

    fun setKeterangan(desc: String) {
        keterangan = desc
        toggleNextButton()
    }

    fun addPriceList(){
        val data = PriceListEntity(
            nama = namaBarang,
            harga = hargaBarang,
            keterangan = keterangan
        )

        runBlocking {
            addPriceListUseCase.invoke(data)
        }

        successSaveEvent.value = true
    }
}