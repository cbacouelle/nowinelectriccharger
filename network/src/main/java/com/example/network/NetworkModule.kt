package com.example.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideOkHttpBuilder(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRetrofit(
        networkJson: Json,
        @BaseUrl.OpenChargeMap baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }
}