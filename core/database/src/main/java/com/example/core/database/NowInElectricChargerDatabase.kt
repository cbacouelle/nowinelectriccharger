package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = true,
)
internal abstract class NowInElectricChargerDatabase : RoomDatabase() {
//    abstract fun topicDao(): TopicDao
}
