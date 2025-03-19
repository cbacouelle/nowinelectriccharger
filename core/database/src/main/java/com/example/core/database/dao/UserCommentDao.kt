package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.UserCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCommentDao {

    @Query("SELECT * from user_comment")
    fun getUserComments(): Flow<List<UserCommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserComment(userCommentEntity: UserCommentEntity)

    @Query("DELETE FROM user_comment")
    fun truncateTable(): Int
}
