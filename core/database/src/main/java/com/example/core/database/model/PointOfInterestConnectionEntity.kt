package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["connection_id", "point_of_interest_id"],
    tableName = "point_of_interest_connection",
    foreignKeys = [
        ForeignKey(
            entity = PointOfInterestEntity::class,
            parentColumns = ["id"],
            childColumns = ["point_of_interest_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ConnectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["connection_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PointOfInterestConnectionEntity(
    @ColumnInfo(name = "connection_id", index = true)
    val connectionId: Int,

    @ColumnInfo(name = "point_of_interest_id", index = true)
    val poiId: Int
)