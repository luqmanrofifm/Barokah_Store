package com.example.barokahstore.core.data.remote.response

object ResponseModel {
    data class Result(
        var status: String = "",
        var message: String = "",
        //var data: List<PriceItem> = emptyList()
    )
    data class PriceItem(
        var id: Int = 0,
        var nama: String = "",
        var price: Int = 0,
        var deskripsi: String = ""
    )
}