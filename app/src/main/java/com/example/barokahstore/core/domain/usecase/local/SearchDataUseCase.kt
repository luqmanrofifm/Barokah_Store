package com.example.barokahstore.core.domain.usecase.local

import androidx.lifecycle.LiveData
import com.example.barokahstore.core.data.local.entity.PriceListEntity
import com.example.barokahstore.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class SearchDataUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {
    fun invoke(word: String): LiveData<List<PriceListEntity>> =localDataRepository.searchData(word)
}