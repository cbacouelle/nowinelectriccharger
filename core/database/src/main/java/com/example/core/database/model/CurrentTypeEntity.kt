package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.CurrentType

@Entity(
    tableName = "current_type",
)
data class CurrentTypeEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "title")
    val title: String,
) {
    fun toDomainModel() = CurrentType(
        ID = id,
        Title = title
    )
}