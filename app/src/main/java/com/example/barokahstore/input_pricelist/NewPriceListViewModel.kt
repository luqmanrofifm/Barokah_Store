package com.example.barokahstore.input_pricelist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.data.remote.ResultApi
import com.example.barokahstore.core.domain.usecase.local.AddPriceListUseCase
import com.example.barokahstore.core.domain.usecase.local.GetPriceListByIdUseCase
import com.example.barokahstore.core.domain.usecase.remote.AddPriceListRemoteUseCase
import com.example.barokahstore.core.domain.usecase.remote.UpdatePriceListRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NewPriceListViewModel @Inject constructor(
    //private val addPriceListUseCase: AddPriceListUseCase,
    private val addPriceListRemoteUseCase: AddPriceListRemoteUseCase,
    private val getPriceListByIdUseCase: GetPriceListByIdUseCase,
    private val updatePriceListRemoteUseCase: UpdatePriceListRemoteUseCase
) : ViewModel()  {
    val isAllFilledEvent = MutableLiveData<Boolean>()
    val successSaveEvent = MutableLiveData<Boolean>()
    val getLocalDataEvent = MutableLiveData<PriceListEntity>()

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
//        val data = PriceListEntity(
//            nama = namaBarang,
//            harga = hargaBarang,
//            keterangan = keterangan
//        )
//
//        runBlocking {
//            addPriceListUseCase.invoke(data)
//        }
//
//        successSaveEvent.value = true

        viewModelScope.launch {
            when ( val response = addPriceListRemoteUseCase.invoke(namaBarang, hargaBarang, keterangan)){
                is ResultApi.Success -> {
                    successSaveEvent.value = true
                }

                is ResultApi.Failure -> {
                    successSaveEvent.value  = false
                    Log.e("API_ERROR", "Input gagal, message: ${response.reason.message}")
                }
            }
        }
    }

    fun getDetailLocalPriceList(id: Int){
        viewModelScope.launch {
            getLocalDataEvent.value = getPriceListByIdUseCase.invoke(id)
        }
    }

    fun updatePriceList(id: Int){
        viewModelScope.launch {
            when ( val response = updatePriceListRemoteUseCase.invoke(namaBarang, hargaBarang, keterangan, id)){
                is ResultApi.Success -> {
                    successSaveEvent.value = true
                }

                is ResultApi.Failure -> {
                    successSaveEvent.value  = false
                    Log.e("API_ERROR", "Input gagal, message: ${response.reason.message}")
                }
            }
        }
    }
}