package com.example.barokahstore.core.domain.usecase.local

import com.example.barokahstore.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class GetListIdPriceListUseCase @Inject constructor(
    private  val localDataRepository: LocalDataRepository
) {
    fun invoke(): List<Int> = localDataRepository.getListId()
}