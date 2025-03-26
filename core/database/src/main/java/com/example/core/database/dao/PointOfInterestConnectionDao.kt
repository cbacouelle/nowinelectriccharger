package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.PointOfInterestConnectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PointOfInterestConnectionDao {

    @Transaction
    @Query("SELECT * from point_of_interest_connection")
    fun getPointOfInterestConnections(): Flow<List<PointOfInterestConnectionEntity>>

    @Insert
    fun savePointOfInterestConnections(pointOfInterestConnectionEntity: PointOfInterestConnectionEntity)

    @Query("DELETE FROM point_of_interest_connection")
    fun truncateTable(): Int
}
