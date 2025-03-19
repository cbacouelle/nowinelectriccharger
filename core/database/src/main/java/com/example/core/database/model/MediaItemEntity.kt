package com.example.core.database.model

import android.provider.MediaStore.Audio.Media
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.core.model.MediaItem
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

data class PopulatedMediaItem(
    @Embedded val entity: MediaItemEntity,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id",
        entity = UserEntity::class
    )
    val user: UserEntity
) {
    fun toDomainModel() = MediaItem(
        ID = entity.id,
        ChargePointID = entity.chargePointId,
        ItemURL = entity.itemUrl,
        ItemThumbnailURL = entity.itemThumbnailUrl,
        Comment = entity.comment,
        IsEnabled = entity.isEnabled,
        DateCreated = entity.dateCreated,
        IsVideo = entity.isVideo,
        IsFeaturedItem = entity.isFeaturedItem,
        IsExternalResource = entity.isExternalResource,
        User = user.toDomainModel()
    )
}