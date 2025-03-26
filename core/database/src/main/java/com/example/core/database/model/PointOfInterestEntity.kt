package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.core.model.PointOfInterest

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

    @ColumnInfo(name = "status_type_id")
    val statusTypeId: Int,

    @ColumnInfo(name = "date_last_status_update")
    val dateLastStatusUpdate: String,

    @ColumnInfo(name = "address_info_id")
    val addressInfoId: Int,

    @ColumnInfo(name = "usage_type_id")
    val usageTypeId: Int,

    @ColumnInfo(name = "data_provider_id")
    val dataProviderId: Int?,

    @ColumnInfo(name = "operator_info_id")
    val operatorInfoId: Int?,
)

data class PopulatedPointOfInterest(
    @Embedded val entity: PointOfInterestEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "charge_point_id",
        entity = UserCommentEntity::class
    )
    val userComments: List<PopulatedUserComment>,

    @Relation(
        parentColumn = "id",
        entityColumn = "charge_point_id",
        entity = MediaItemEntity::class
    )
    val mediaItems: List<PopulatedMediaItem>,

    @Relation(
        parentColumn = "id",
        entityColumn = "charge_point_id",
        entity = AddressInfoEntity::class
    )
    val addressInfo: AddressInfoEntity,

    @Relation(
        parentColumn = "operator_info_id",
        entityColumn = "id",
        entity = OperatorInfoEntity::class
    )
    val operatorInfo: PopulatedOperatorInfo?,

    @Relation(
        parentColumn = "usage_type_id",
        entityColumn = "id",
        entity = UsageTypeEntity::class
    )
    val usageType: UsageTypeEntity,

    @Relation(
        parentColumn = "data_provider_id",
        entityColumn = "id",
        entity = DataProviderEntity::class
    )
    val dataProvider: DataProviderEntity,

    @Relation(
        parentColumn = "status_type_id",
        entityColumn = "id",
        entity = StatusTypeEntity::class
    )
    val statusType: StatusTypeEntity,

    @Relation(
        entity = ConnectionEntity::class, parentColumn = "id", entityColumn = "id",
        associateBy = Junction(
            value = PointOfInterestConnectionEntity::class, parentColumn = "point_of_interest_id", entityColumn = "connection_id"
        )
    )
    val connections: List<PopulatedConnection>,
) {
    fun toDomainModel(): PointOfInterest = PointOfInterest(
        ID = entity.id,
        UUID = entity.uuid,
        IsRecentlyVerified = entity.isRecentlyVerified,
        DateLastVerified = entity.dateLastVerified,
        UsageCost = entity.usageCost,
        NumberOfPoints = entity.numberOfPoints,
        GeneralComments = entity.generalComments,
        StatusType = statusType.toDomainModel(),
        DateLastStatusUpdate = entity.dateLastStatusUpdate,
        UserComments = userComments.map { it.toDomainModel() },
        MediaItems = mediaItems.map { it.toDomainModel() },
        AddressInfo = addressInfo.toDomainModel(),
        UsageTye = usageType.toDomainModel(),
        DataProvider = dataProvider.toDomainModel(),
        Connections = connections.map { it.toDomainModel() },
        OperatorInfo = operatorInfo?.toDomainModel()
    )
}