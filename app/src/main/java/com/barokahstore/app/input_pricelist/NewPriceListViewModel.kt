package com.barokahstore.app.input_pricelist

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barokahstore.app.core.data.local.entity.PriceListEntity
import com.barokahstore.app.core.data.remote.ResultApi
import com.barokahstore.app.core.domain.usecase.local.GetPriceListByIdUseCase
import com.barokahstore.app.core.domain.usecase.remote.AddPriceListRemoteUseCase
import com.barokahstore.app.core.domain.usecase.remote.UpdatePriceListRemoteUseCase
import com.barokahstore.app.core.utils.showErrorDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    private var keteranganSatuan = ""

    init {
        isAllFilledEvent.value = false
        successSaveEvent.value = false
        loadingEvent.value = false
    }
    private fun toggleNextButton() {
        isAllFilledEvent.value = namaBarang.isNotEmpty() && hargaBarang.toString() != "0" && keteranganSatuan.isNotEmpty()
    }

    fun setNamaBarang(name: String) {
        namaBarang = name
        toggleNextButton()
    }

    fun setHargaBarang(harga: Int) {
        hargaBarang = harga
        toggleNextButton()
    }

    fun setKeteranganSatuan(keterangan: String) {
        keteranganSatuan = keterangan
        toggleNextButton()
    }

    fun setKeterangan(desc: String) {
        keterangan = desc
        toggleNextButton()
    }

    fun addPriceList(context: Context){
        viewModelScope.launch {
            loadingEvent.value = true
            when ( val response = addPriceListRemoteUseCase.invoke(namaBarang, hargaBarang, keteranganSatuan, keterangan)){
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
            when ( val response = updatePriceListRemoteUseCase.invoke(namaBarang, hargaBarang,  keteranganSatuan, keterangan, id)){
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