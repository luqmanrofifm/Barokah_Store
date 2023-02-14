package com.barokahstore.app.core.data.remote.response

object ResponseModel {
    data class Result(
        var status: String = "",
        var message: String = "",
        var list_id: List<Int> = emptyList(),
        var data: List<PriceItem> = emptyList()
    )
    data class PriceItem(
        var id: Int = 0,
        var nama: String = "",
        var price: Int = 0,
        var satuan: String = "",
        var deskripsi: String = ""
    )
}