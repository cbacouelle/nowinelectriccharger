package com.example.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.core.database.NowInElectricChargerDatabase
import com.example.core.database.dao.AddressInfoDao
import com.example.core.database.dao.CommentTypeDao
import com.example.core.database.dao.MediaItemDao
import com.example.core.database.dao.PointOfInterestDao
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
}