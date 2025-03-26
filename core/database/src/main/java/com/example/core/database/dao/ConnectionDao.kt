package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.ConnectionEntity
import com.example.core.database.model.PopulatedConnection
import kotlinx.coroutines.flow.Flow

@Dao
interface ConnectionDao {

    @Transaction
    @Query("SELECT * from connection")
    fun getConnections(): Flow<List<PopulatedConnection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveConnection(connectionEntity: ConnectionEntity)

    @Query("DELETE FROM connection")
    fun truncateTable(): Int
}
