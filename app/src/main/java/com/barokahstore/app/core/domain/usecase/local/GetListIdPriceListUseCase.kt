package com.barokahstore.app.core.domain.usecase.local

import com.barokahstore.app.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class GetListIdPriceListUseCase @Inject constructor(
    private  val localDataRepository: LocalDataRepository
) {
    fun invoke(): List<Int> = localDataRepository.getListId()
}