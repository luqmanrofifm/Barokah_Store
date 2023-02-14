package com.barokahstore.app.core.domain.usecase.local

import androidx.lifecycle.LiveData
import com.barokahstore.app.core.data.local.entity.PriceListEntity
import com.barokahstore.app.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class GetAllPriceListUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {
    fun invoke():LiveData<List<PriceListEntity>> =localDataRepository.getDataALl()
}