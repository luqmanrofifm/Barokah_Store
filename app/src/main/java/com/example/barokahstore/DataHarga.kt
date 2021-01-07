package com.example.barokahstore

import java.util.*
import kotlin.collections.ArrayList

object DataHarga {
    private val namaBarang = arrayOf("aqua",
                "roti",
                "obat")

    private val harga = arrayOf("18000",
                "5000",
                "2000")

    private val rincian = arrayOf("aaaaaaaaa",
                "bbbbbbbbbb",
                "cccccccccc")

    val listData: ArrayList<HargaBarang>
        get() {
            val list = arrayListOf<HargaBarang>()
            for (position in namaBarang.indices) {
                val data = HargaBarang()
                data.judul = namaBarang[position]
                data.harga = harga[position]
                data.keterangan = rincian[position]
                list.add(data)
            }
            return list
        }
}