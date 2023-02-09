package com.example.barokahstore

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.data.remote.ResultApi
import com.example.barokahstore.core.data.remote.response.ResponseModel
import com.example.barokahstore.core.domain.usecase.local.*
import com.example.barokahstore.core.domain.usecase.remote.DeletePriceListRemoteUseCase
import com.example.barokahstore.core.domain.usecase.remote.GetPriceListRemoteUseCase
import com.example.barokahstore.core.utils.showErrorDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllPriceListUseCase: GetAllPriceListUseCase,
    private val getPriceListRemoteUseCase: GetPriceListRemoteUseCase,
    private val getListIdPriceListUseCase: GetListIdPriceListUseCase,
    private val addPriceListUseCase: AddPriceListUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase,
    private val deletePriceListRemoteUseCase: DeletePriceListRemoteUseCase,
    private val updatePriceListUseCase: UpdatePriceListUseCase,
    private val searchDataUseCase: SearchDataUseCase,
): ViewModel() {
    lateinit var allPriceList: LiveData<List<PriceListEntity>>
    val loadingEvent = MutableLiveData<Boolean>()

    init {
        loadingEvent.value = false
    }

    private var wordSearch = ""
    fun setWordSearch(word: String) {
        wordSearch = word
    }

    fun getPriceList(){
        viewModelScope.launch{
            allPriceList = getAllPriceListUseCase.invoke()
        }
    }

    fun searchDataPriceList(word: String){
        viewModelScope.launch {
            allPriceList = searchDataUseCase.invoke(word)
        }
    }

    fun deletePriceList(id: Int){
        viewModelScope.launch {
            loadingEvent.value = true

            when ( val response = deletePriceListRemoteUseCase.invoke(id)){
                is ResultApi.Success -> {
                    //successSaveEvent.value = true
                    deleteByIdUseCase.invoke(id)
                }

                is ResultApi.Failure -> {
                    //successSaveEvent.value  = false
                    Log.e("API_ERROR", "Input gagal, message: ${response.reason.message}")
                }
            }
            loadingEvent.value = false
        }
    }

    fun synchronizeData(context: Context, adapter: PriceListAdapter){
        val listId = getListIdPriceListUseCase.invoke()
        var listIdRemote: List<Int>
        var listDataRemote: List<ResponseModel.PriceItem>

        viewModelScope.launch {
            loadingEvent.value = true

            when ( val response = getPriceListRemoteUseCase.invoke()){
                is ResultApi.Success -> {
                    //successSaveEvent.value = true
                    Log.d("API_SUKSES", "sukses gan")
                    if (response.value.data.isNotEmpty()){
                        listIdRemote = response.value.list_id
                        listDataRemote = response.value.data

                        val newData = listIdRemote.subtract(listId.intersect(listIdRemote.toSet()))
                        val deletedData = listId.subtract(listId.intersect(listIdRemote.toSet()))
                        val intersectData = listId.intersect(listIdRemote.toSet())

                        if (intersectData.isNotEmpty()){
                            val filterData = listDataRemote.filter { intersectData.contains(it.id) }
                            filterData.forEach {data ->

                                val entity = PriceListEntity(
                                    id = data.id,
                                    nama = data.nama,
                                    harga = data.price,
                                    satuan = data.satuan,
                                    keterangan = if(data.deskripsi == null){""}else{data.deskripsi}
                                )

                                runBlocking {
                                    updatePriceListUseCase.invoke(entity)
                                }
                            }
                        }

                        if (newData.isNotEmpty()){
                            val filterData = listDataRemote.filter { newData.contains(it.id) }
                            filterData.forEach {data ->
                                val entity = PriceListEntity(
                                    id = data.id,
                                    nama = data.nama,
                                    harga = data.price,
                                    satuan = data.satuan,
                                    keterangan = if(data.deskripsi == null){""}else{data.deskripsi}
                                )

                                runBlocking {
                                    addPriceListUseCase.invoke(entity)
                                }
                            }
                        }

                        if (deletedData.isNotEmpty()){
                            deletedData.forEach { id ->
                                runBlocking {
                                    deleteByIdUseCase.invoke(id)
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                is ResultApi.Failure -> {
                    //successSaveEvent.value  = false
                    Log.e("API_ERROR", "Input gagal, message: ${response.reason.message}")
                    response.reason.message?.let {
                        showErrorDialog(context, it)
                    }
                }
            }
            loadingEvent.value = false
        }
    }
}