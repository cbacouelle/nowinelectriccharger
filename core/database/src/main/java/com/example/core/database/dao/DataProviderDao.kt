package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.DataProviderEntity
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.PopulatedPointOfInterest
import com.example.core.database.model.UsageTypeEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.model.PointOfInterest
import com.example.core.model.UsageType
import kotlinx.coroutines.flow.Flow

@Dao
interface DataProviderDao {

    @Transaction
    @Query("SELECT * from data_provider")
    fun getDataProviders(): Flow<List<DataProviderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDataProvider(dataProviderEntity: DataProviderEntity)

    @Query("DELETE FROM data_provider")
    fun truncateTable(): Int
}
