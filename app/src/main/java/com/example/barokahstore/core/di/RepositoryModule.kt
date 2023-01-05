package com.example.barokahstore.core.di

import com.example.barokahstore.core.data.local.LocalDataSourceImpl
import com.example.barokahstore.core.domain.repository.LocalDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class RepositoryModule {
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class LocalDataModule {

        @Binds
        abstract fun provideLocalDataRepository(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataRepository
    }
}