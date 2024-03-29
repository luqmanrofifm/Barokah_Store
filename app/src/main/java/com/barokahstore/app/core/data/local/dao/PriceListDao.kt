package com.barokahstore.app.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barokahstore.app.core.data.local.entity.PriceListEntity

@Dao
interface PriceListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: PriceListEntity)

    @Query("DELETE FROM price_list")
    suspend fun deleteAllData()

    @Query("DELETE FROM price_list where id =:id")
    suspend fun deleteData(id: Int)

    @Query("SELECT * FROM price_list ORDER BY nama")
    fun getAllPriceList(): LiveData<List<PriceListEntity>>

    @Query("SELECT * FROM price_list where nama LIKE :word")
    fun searchPriceList(word: String): LiveData<List<PriceListEntity>>

    @Query("SELECT * FROM price_list")
    fun getAllPriceListNotLive(): List<PriceListEntity>

    @Query("SELECT id FROM price_list")
    fun getIdPriceList(): List<Int>

    @Query("SELECT * FROM price_list where id =:id")
    fun getDataById(id: Int): PriceListEntity

    @Query("UPDATE price_list SET nama=:nama, harga=:harga, satuan=:satuan, keterangan=:keterangan WHERE id=:id")
    suspend fun updatePriceList( id: Int, nama: String, harga: Int, satuan: String, keterangan: String)
}