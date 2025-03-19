package com.example.feature.chargers.data.di

import com.example.feature.chargers.data.repository.OfflineFirstElectricChargerRepository
import com.example.feature.chargers.domain.ElectricChargerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ElectricChargerRepositoryModule {

    @Binds
    fun bindsElectricChargerRepository(
        electricChargerRepository: OfflineFirstElectricChargerRepository,
    ): ElectricChargerRepository
}
