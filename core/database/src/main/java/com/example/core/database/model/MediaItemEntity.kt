package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.core.model.User

@Entity(
    tableName = "media_item",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
        ),
        ForeignKey(
            entity = PointOfInterestEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("charge_point_id"),
        )
    ]
)
data class MediaItemEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "charge_point_id", index = true)
    val chargePointId: Int,

    @ColumnInfo(name = "item_url")
    val itemUrl: String,

    @ColumnInfo(name = "item_thumbnail_url")
    val itemThumbnailUrl: String,

    @ColumnInfo(name = "comment")
    val comment: String,

    @ColumnInfo(name = "is_enabled")
    val isEnabled: Boolean,

    @ColumnInfo(name = "date_created")
    val dateCreated: String,

    @ColumnInfo(name = "is_video")
    val isVideo: Boolean,

    @ColumnInfo(name = "is_featured_item")
    val isFeaturedItem: Boolean,

    @ColumnInfo(name = "is_external_resource")
    val isExternalResource: Boolean,

    @ColumnInfo(name = "user_id", index = true)
    val userId: Int,
)