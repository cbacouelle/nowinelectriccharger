package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.core.model.StatusType

@Entity(
    tableName = "point_of_interest",
)
data class PointOfInterestEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "uuid")
    val uuid: String,

    @ColumnInfo(name = "is_recently_verified")
    val isRecentlyVerified: Boolean,

    @ColumnInfo(name = "date_last_verified")
    val dateLastVerified: String,

    @ColumnInfo(name = "usage_cost")
    val usageCost: String?,

    @ColumnInfo(name = "number_of_points")
    val numberOfPoints: Int?,

    @ColumnInfo(name = "general_comments")
    val generalComments: String?,

    @ColumnInfo(name = "status_type")
    val statusType: StatusType,

    @ColumnInfo(name = "date_last_status_update")
    val dateLastStatusUpdate: String,

    @ColumnInfo(name = "address_info_id")
    val addressInfoId: String,
)

data class PopulatedPointOfInterest(
    @Embedded val entity: PointOfInterestEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "charge_point_id",
        entity = UserCommentEntity::class
    )
    val userComments: List<UserCommentEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "charge_point_id",
        entity = MediaItemEntity::class
    )
    val mediaItems: List<MediaItemEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "charge_point_id",
        entity = AddressInfoEntity::class
    )
    val addressInfo: AddressInfoEntity
)