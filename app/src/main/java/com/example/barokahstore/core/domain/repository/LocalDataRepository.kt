package com.example.barokahstore.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.barokahstore.core.data.local.entity.PriceListEntity

interface LocalDataRepository {
    fun addPriceList(data: PriceListEntity)

    fun deleteAll()

    fun deleteById(id: Int)

    fun getDataALl(): LiveData<List<PriceListEntity>>

    fun getDataAllNotLive(): List<PriceListEntity>

    fun getListId(): List<Int>

    fun getById(id: Int): PriceListEntity

    fun updatePriceList(data: PriceListEntity)

}