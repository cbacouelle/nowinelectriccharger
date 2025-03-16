package com.example.feature.location.data.di

import com.example.feature.location.data.DefaultLocationDataRepository
import com.example.feature.location.data.LocationDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocationDataModule {

    @Binds
    fun bindsLocationDataRepository(
        locationDataRepository: DefaultLocationDataRepository,
    ): LocationDataRepository

}
