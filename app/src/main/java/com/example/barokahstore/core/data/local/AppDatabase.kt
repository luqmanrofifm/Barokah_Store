package com.example.barokahstore.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.barokahstore.core.data.local.dao.PriceListDao
import com.example.barokahstore.core.data.local.entity.PriceListEntity

@Database(
    entities = [
        PriceListEntity::class,
    ],
    version = 4,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    //Declare DAO
    abstract fun priceListDao(): PriceListDao
}