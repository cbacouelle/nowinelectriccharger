package com.example.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.core.datastore_proto.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesLocationDataStore(
        @ApplicationContext context: Context,
        locationSerializer: LocationSerializer,
    ): DataStore<Location> {
        return DataStoreFactory.create(
            serializer = locationSerializer,
        ) {
            context.dataStoreFile("location.pb")
        }
    }
}
