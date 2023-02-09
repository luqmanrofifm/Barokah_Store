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

    override fun deleteById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            priceListDao.deleteData(id)
        }
    }

    override fun getDataALl(): LiveData<List<PriceListEntity>> {
        return priceListDao.getAllPriceList()
    }

    override fun getDataAllNotLive(): List<PriceListEntity> {
        return priceListDao.getAllPriceListNotLive()
    }

    override fun getListId(): List<Int> {
        return priceListDao.getIdPriceList()
    }

    override fun getById(id: Int): PriceListEntity {
        return priceListDao.getDataById(id)
    }

    override fun updatePriceList(data: PriceListEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            priceListDao.updatePriceList(id = data.id, nama = data.nama, harga = data.harga, satuan = data.satuan, keterangan = data.keterangan)
        }
    }

    override fun searchData(word: String): LiveData<List<PriceListEntity>> {
        return priceListDao.searchPriceList("%${word}%")
    }
}