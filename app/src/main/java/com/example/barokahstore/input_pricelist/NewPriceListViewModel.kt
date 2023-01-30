package com.example.barokahstore.input_pricelist

import android.content.Context
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
import com.example.barokahstore.core.utils.showErrorDialog
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

    val loadingEvent = MutableLiveData<Boolean>()

    var finishActivity: (() -> Unit)? = null

    private var namaBarang = ""
    private var hargaBarang = 0
    private var keterangan = ""

    init {
        isAllFilledEvent.value = false
        successSaveEvent.value = false
        loadingEvent.value = false
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

    fun addPriceList(context: Context){
        viewModelScope.launch {
            loadingEvent.value = true
            when ( val response = addPriceListRemoteUseCase.invoke(namaBarang, hargaBarang, keterangan)){
                is ResultApi.Success -> {
                    loadingEvent.value = false
                    successSaveEvent.value = true
                }

                is ResultApi.Failure -> {
                    //successSaveEvent.value  = false
                    loadingEvent.value = false
                    Log.e("API_ERROR", "Input gagal, message: ${response.reason.message}")
                    response.reason.message?.let {
                        showErrorDialog(context, it, finishActivity)
                    }
                }
            }

        }
    }

    fun getDetailLocalPriceList(id: Int){
        viewModelScope.launch {
            getLocalDataEvent.value = getPriceListByIdUseCase.invoke(id)
        }
    }

    fun updatePriceList(id: Int, context: Context){
        viewModelScope.launch {
            loadingEvent.value = true
            when ( val response = updatePriceListRemoteUseCase.invoke(namaBarang, hargaBarang, keterangan, id)){
                is ResultApi.Success -> {
                    loadingEvent.value = false
                    successSaveEvent.value = true
                }

                is ResultApi.Failure -> {
//                    successSaveEvent.value  = false
                    loadingEvent.value = false
                    Log.e("API_ERROR", "Input gagal, message: ${response.reason.message}")
                    response.reason.message?.let {
                        showErrorDialog(context, it, finishActivity)
                    }
                }
            }

        }
    }
}