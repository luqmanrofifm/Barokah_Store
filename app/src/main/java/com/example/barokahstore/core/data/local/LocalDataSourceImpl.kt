package com.example.barokahstore.core.data.local

import androidx.lifecycle.LiveData
import com.example.barokahstore.core.data.local.dao.PriceListDao
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.domain.repository.LocalDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private  val priceListDao: PriceListDao
): LocalDataRepository {
    override fun addPriceList(data: PriceListEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            priceListDao.insert(data)
        }
    }

    override fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            priceListDao.deleteAllData()
        }
    }

    override fun getDataALl(): LiveData<List<PriceListEntity>> {
        return priceListDao.getAllPriceList()
    }
}