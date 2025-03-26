package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.ConnectionTypeEntity
import com.example.core.database.model.DataProviderEntity
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.PointOfInterestConnectionEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.PopulatedPointOfInterest
import com.example.core.database.model.StatusTypeEntity
import com.example.core.database.model.UsageTypeEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.model.PointOfInterest
import com.example.core.model.UsageType
import kotlinx.coroutines.flow.Flow

@Dao
interface ConnectionTypeDao {

    @Transaction
    @Query("SELECT * from connection_type")
    fun getConnectionTypes(): Flow<List<ConnectionTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveConnectionType(connectionTypeEntity: ConnectionTypeEntity)

    @Query("DELETE FROM connection_type")
    fun truncateTable(): Int
}
