package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.Level

@Entity(
    tableName = "level",
)
data class LevelEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "is_fast_chargeable")
    val isFastChargeable: Boolean,

    @ColumnInfo(name = "comments")
    val comments: String?,
) {
    fun toDomainModel() = Level(
        ID = id,
        Title = title,
        IsFastChargeCapable = isFastChargeable,
        Comments = comments
    )
}