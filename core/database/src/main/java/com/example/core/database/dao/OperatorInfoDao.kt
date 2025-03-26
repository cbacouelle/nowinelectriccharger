package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.model.CurrentTypeEntity
import com.example.core.database.model.OperatorInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OperatorInfoDao {

    @Query("SELECT * from operator_info")
    fun getOperatorInfos(): Flow<List<OperatorInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOperatorInfo(operatorInfoEntity: OperatorInfoEntity)

    @Query("DELETE FROM operator_info")
    fun truncateTable(): Int
}
