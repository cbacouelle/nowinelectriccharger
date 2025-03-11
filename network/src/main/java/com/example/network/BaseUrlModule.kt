package com.example.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object BaseUrlModule {

    @Provides
    @Singleton
    @JvmStatic
    @BaseUrl.OpenChargeMap
    internal fun provideOpenChargeMapUrl(): String = "https://api.openchargemap.io/v3/"

}

object BaseUrl {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class OpenChargeMap

}