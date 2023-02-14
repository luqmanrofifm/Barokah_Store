package com.barokahstore.app.core.di

import android.content.Context
import androidx.room.Room
import com.barokahstore.app.core.data.local.AppDatabase
import com.barokahstore.app.core.data.local.dao.PriceListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "BarokahStore.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFieldDataDao(db: AppDatabase): PriceListDao = db.priceListDao()
}