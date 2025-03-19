package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.CommentType

@Entity(
    tableName = "comment_type",
)
data class CommentTypeEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "title")
    val title: String,
) {
    fun toDomainModel() = CommentType(
        ID = id,
        Title = title
    )
}