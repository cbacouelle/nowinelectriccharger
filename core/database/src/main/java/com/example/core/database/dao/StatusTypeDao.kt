package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.StatusTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatusTypeDao {

    @Transaction
    @Query("SELECT * from status_type")
    fun getStatusTypes(): Flow<List<StatusTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStatusType(statusTypeEntity: StatusTypeEntity)

    @Query("DELETE FROM status_type")
    fun truncateTable(): Int
}
