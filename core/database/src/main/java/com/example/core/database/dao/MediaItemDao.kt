package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.UserCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaItemDao {

    @Query("SELECT * from media_item")
    fun getMediaItems(): Flow<List<MediaItemEntity>>
}
