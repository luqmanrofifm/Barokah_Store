package com.barokahstore.app.core.di

import com.barokahstore.app.core.data.local.LocalDataSourceImpl
import com.barokahstore.app.core.data.remote.RemoteDataSourceImpl
import com.barokahstore.app.core.domain.repository.LocalDataRepository
import com.barokahstore.app.core.domain.repository.RemoteDataRepository
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

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RemoteDataModule {

        @Binds
        abstract fun provideRemoteDataRepository(
            remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataRepository
    }
}