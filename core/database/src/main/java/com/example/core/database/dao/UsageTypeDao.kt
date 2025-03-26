package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.PopulatedPointOfInterest
import com.example.core.database.model.UsageTypeEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.model.PointOfInterest
import com.example.core.model.UsageType
import kotlinx.coroutines.flow.Flow

@Dao
interface UsageTypeDao {

    @Transaction
    @Query("SELECT * from usage_type")
    fun getUsageTypes(): Flow<List<UsageTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsageType(usageType: UsageTypeEntity)

    @Query("DELETE FROM usage_type")
    fun truncateTable(): Int
}
