package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.CommentTypeEntity
import com.example.core.database.model.LevelEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    @Query("SELECT * from level")
    fun getLevels(): Flow<List<LevelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLevel(levelEntity: LevelEntity)

    @Query("DELETE FROM level")
    fun truncateTable(): Int
}
