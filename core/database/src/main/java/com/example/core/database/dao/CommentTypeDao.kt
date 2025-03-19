package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.CommentTypeEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentTypeDao {

    @Query("SELECT * from comment_type")
    fun getCommentTypes(): Flow<List<CommentTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCommentType(commentTypeEntity: CommentTypeEntity)

    @Query("DELETE FROM comment_type")
    fun truncateTable(): Int
}
