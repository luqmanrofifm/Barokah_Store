package com.barokahstore.app.core.domain.usecase.local

import com.barokahstore.app.core.data.local.entity.PriceListEntity
import com.barokahstore.app.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class GetPriceListByIdUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {
    fun invoke(id: Int): PriceListEntity = localDataRepository.getById(id)
}