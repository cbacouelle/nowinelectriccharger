package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "comment_type",
)
data class CommentTypeEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "title")
    val title: String,
)