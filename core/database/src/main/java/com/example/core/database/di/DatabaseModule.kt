package com.example.core.database.di

import android.content.Context
import android.graphics.Path.Op
import androidx.room.Room
import com.example.core.database.NowInElectricChargerDatabase
import com.example.core.database.dao.AddressInfoDao
import com.example.core.database.dao.CommentTypeDao
import com.example.core.database.dao.ConnectionDao
import com.example.core.database.dao.ConnectionTypeDao
import com.example.core.database.dao.CurrentTypeDao
import com.example.core.database.dao.DataProviderDao
import com.example.core.database.dao.LevelDao
import com.example.core.database.dao.MediaItemDao
import com.example.core.database.dao.OperatorInfoDao
import com.example.core.database.dao.PointOfInterestConnectionDao
import com.example.core.database.dao.PointOfInterestDao
import com.example.core.database.dao.StatusTypeDao
import com.example.core.database.dao.UsageTypeDao
import com.example.core.database.dao.UserCommentDao
import com.example.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val APP_DATABASE_NAME = "app_now_in_electric_charger"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): NowInElectricChargerDatabase = Room.databaseBuilder(
        context,
        NowInElectricChargerDatabase::class.java,
        APP_DATABASE_NAME,
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(database: NowInElectricChargerDatabase): UserDao = database.userDao()

    @Singleton
    @Provides
    fun provideUserCommentDao(database: NowInElectricChargerDatabase): UserCommentDao =
        database.userCommentDao()


    @Singleton
    @Provides
    fun provideAddressInfoDao(database: NowInElectricChargerDatabase): AddressInfoDao =
        database.addressInfoDao()

    @Singleton
    @Provides
    fun provideCommentTypeDao(database: NowInElectricChargerDatabase): CommentTypeDao =
        database.commentTypeDao()

    @Singleton
    @Provides
    fun provideMediaItemDao(database: NowInElectricChargerDatabase): MediaItemDao =
        database.mediaItemDao()

    @Singleton
    @Provides
    fun providePointOfInterestDao(database: NowInElectricChargerDatabase): PointOfInterestDao =
        database.pointOfInterestDao()

    @Singleton
    @Provides
    fun provideUsageTypeDao(database: NowInElectricChargerDatabase): UsageTypeDao =
        database.usageTypeDao()

    @Singleton
    @Provides
    fun provideDataProviderDao(database: NowInElectricChargerDatabase): DataProviderDao =
        database.dataProviderDao()

    @Singleton
    @Provides
    fun provideStatusTypeDao(database: NowInElectricChargerDatabase): StatusTypeDao =
        database.statusTypeDao()

    @Singleton
    @Provides
    fun provideConnectionDao(database: NowInElectricChargerDatabase): ConnectionDao =
        database.connectionDao()

    @Singleton
    @Provides
    fun providePointOfInterestConnectionDao(database: NowInElectricChargerDatabase): PointOfInterestConnectionDao =
        database.pointOfInterestConnectionDao()

    @Singleton
    @Provides
    fun provideConnectionTypeDao(database: NowInElectricChargerDatabase): ConnectionTypeDao =
        database.connectionTypeDao()

    @Singleton
    @Provides
    fun provideLevelDao(database: NowInElectricChargerDatabase): LevelDao =
        database.levelDao()

    @Singleton
    @Provides
    fun provideCurrentTypeDao(database: NowInElectricChargerDatabase): CurrentTypeDao =
        database.currentTypeDao()

    @Singleton
    @Provides
    fun provideOperatorInfoDao(database: NowInElectricChargerDatabase): OperatorInfoDao =
        database.operatorInfoDao()
}