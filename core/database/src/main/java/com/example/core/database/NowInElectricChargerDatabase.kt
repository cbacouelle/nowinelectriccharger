package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.AddressInfoDao
import com.example.core.database.dao.CommentTypeDao
import com.example.core.database.dao.MediaItemDao
import com.example.core.database.dao.PointOfInterestDao
import com.example.core.database.dao.UserCommentDao
import com.example.core.database.dao.UserDao
import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.CommentTypeEntity
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.PointOfInterestEntity
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
}

