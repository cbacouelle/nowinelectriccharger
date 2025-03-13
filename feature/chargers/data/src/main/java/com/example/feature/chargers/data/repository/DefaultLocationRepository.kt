package com.example.feature.chargers.data.repository

import android.content.Context
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLocationRepository @Inject constructor(
    private val context: Context
) {

    private val USERNAME_KEY = stringPreferencesKey("username")


    suspend fun saveUsername(name: String) {
        context.dataStoreFile("ok")
    }
}