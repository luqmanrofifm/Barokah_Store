package com.barokahstore.app.core.domain.usecase.local

import com.barokahstore.app.core.domain.repository.LocalDataRepository
import javax.inject.Inject

class DeleteByIdUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {
    fun invoke(id: Int){
        localDataRepository.deleteById(id)
    }
}