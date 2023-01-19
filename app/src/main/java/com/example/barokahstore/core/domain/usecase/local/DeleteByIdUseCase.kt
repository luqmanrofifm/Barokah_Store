package com.example.barokahstore.core.domain.usecase.local

import com.example.barokahstore.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class DeleteByIdUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {
    fun invoke(id: Int){
        localDataRepository.deleteById(id)
    }
}