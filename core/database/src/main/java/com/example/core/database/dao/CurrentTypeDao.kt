package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.model.CurrentTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentTypeDao {

    @Query("SELECT * from current_type")
    fun getCurrentTypes(): Flow<List<CurrentTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentType(currentTypeEntity: CurrentTypeEntity)

    @Query("DELETE FROM current_type")
    fun truncateTable(): Int
}
