package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressInfoDao {

    @Query("SELECT * from address_info")
    fun getAddressInfos(): Flow<List<AddressInfoEntity>>
}
