package com.barokahstore.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_list")
data class PriceListEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nama: String,
    var harga: Int,
    var satuan: String,
    var keterangan: String
)