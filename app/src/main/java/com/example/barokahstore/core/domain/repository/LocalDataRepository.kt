package com.example.barokahstore.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.barokahstore.core.data.local.entity.PriceListEntity

interface LocalDataRepository {
    fun addPriceList(data: PriceListEntity)

    fun deleteAll()

    fun getDataALl(): LiveData<List<PriceListEntity>>
}