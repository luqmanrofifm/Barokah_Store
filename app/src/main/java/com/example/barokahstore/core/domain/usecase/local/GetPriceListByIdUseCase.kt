package com.example.barokahstore.core.domain.usecase.local

import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class GetPriceListByIdUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {
    fun invoke(id: Int): PriceListEntity = localDataRepository.getById(id)
}