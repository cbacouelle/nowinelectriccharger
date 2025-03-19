package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.PopulatedPointOfInterest
import com.example.core.database.model.UserCommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PointOfInterestDao {

    @Transaction
    @Query("SELECT * from point_of_interest")
    fun getPointOfInterests(): Flow<List<PopulatedPointOfInterest>>
}
