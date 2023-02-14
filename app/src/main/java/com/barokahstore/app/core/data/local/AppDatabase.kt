package com.barokahstore.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barokahstore.app.core.data.local.entity.PriceListEntity
import com.barokahstore.app.core.data.local.dao.PriceListDao

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