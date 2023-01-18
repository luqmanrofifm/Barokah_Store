package com.example.barokahstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.domain.usecase.local.GetAllPriceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllPriceListUseCase: GetAllPriceListUseCase
): ViewModel() {
    lateinit var allPriceList: LiveData<List<PriceListEntity>>

    fun getPriceList(){
        viewModelScope.launch{
            allPriceList = getAllPriceListUseCase.invoke()
        }
    }
}