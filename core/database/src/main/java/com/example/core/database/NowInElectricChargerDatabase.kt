package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
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
import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.CommentTypeEntity
import com.example.core.database.model.ConnectionEntity
import com.example.core.database.model.ConnectionTypeEntity
import com.example.core.database.model.CurrentTypeEntity
import com.example.core.database.model.DataProviderEntity
import com.example.core.database.model.LevelEntity
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.OperatorInfoEntity
import com.example.core.database.model.PointOfInterestConnectionEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.StatusTypeEntity
import com.example.core.database.model.UsageTypeEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.database.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
        UserCommentEntity::class,
        AddressInfoEntity::class,
        CommentTypeEntity::class,
        MediaItemEntity::class,
        PointOfInterestEntity::class,
        UsageTypeEntity::class,
        DataProviderEntity::class,
        StatusTypeEntity::class,
        ConnectionEntity::class,
        PointOfInterestConnectionEntity::class,
        ConnectionTypeEntity::class,
        LevelEntity::class,
        CurrentTypeEntity::class,
        OperatorInfoEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class NowInElectricChargerDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userCommentDao(): UserCommentDao
    abstract fun addressInfoDao(): AddressInfoDao
    abstract fun commentTypeDao(): CommentTypeDao
    abstract fun mediaItemDao(): MediaItemDao
    abstract fun pointOfInterestDao(): PointOfInterestDao
    abstract fun usageTypeDao(): UsageTypeDao
    abstract fun dataProviderDao(): DataProviderDao
    abstract fun statusTypeDao(): StatusTypeDao
    abstract fun connectionDao(): ConnectionDao
    abstract fun pointOfInterestConnectionDao(): PointOfInterestConnectionDao
    abstract fun connectionTypeDao(): ConnectionTypeDao
    abstract fun levelDao(): LevelDao
    abstract fun currentTypeDao(): CurrentTypeDao
    abstract fun operatorInfoDao(): OperatorInfoDao
}

