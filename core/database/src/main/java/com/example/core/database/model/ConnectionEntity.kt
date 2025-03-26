package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.core.model.Connection

@Entity(
    tableName = "connection",
)
data class ConnectionEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "connection_type_id", index = true)
    val connectionTypeId: Int,

    @ColumnInfo(name = "status_type_id", index = true)
    val statusTypeId: Int?,

    @ColumnInfo(name = "level_id", index = true)
    val levelId: Int,

    @ColumnInfo(name = "current_type_id", index = true)
    val currentTypeId: Int?,

    @ColumnInfo(name = "reference")
    val reference: String?,

    @ColumnInfo(name = "amps")
    val amps: Int?,

    @ColumnInfo(name = "voltage")
    val voltage: Int?,

    @ColumnInfo(name = "power_kw")
    val powerKw: Int?,

    @ColumnInfo(name = "quantity")
    val quantity: Int?,

    @ColumnInfo(name = "comments")
    val comments: String?,

)

data class PopulatedConnection(
    @Embedded val entity: ConnectionEntity,

    @Relation(
        parentColumn = "connection_type_id",
        entityColumn = "id",
        entity = ConnectionTypeEntity::class
    )
    val connectionType: ConnectionTypeEntity,

    @Relation(
        parentColumn = "status_type_id",
        entityColumn = "id",
        entity = StatusTypeEntity::class
    )
    val statusType: StatusTypeEntity?,

    @Relation(
        parentColumn = "level_id",
        entityColumn = "id",
        entity = LevelEntity::class
    )
    val level: LevelEntity,

    @Relation(
        parentColumn = "current_type_id",
        entityColumn = "id",
        entity = CurrentTypeEntity::class
    )
    val currentType: CurrentTypeEntity?,
) {
    fun toDomainModel() = Connection(
        Id = entity.id,
        ConnectionTypeID = connectionType.id,
        ConnectionType = connectionType.toDomainModel(),
        Reference = entity.reference,
        StatusTypeID = entity.statusTypeId,
        Amps = entity.amps,
        Voltage = entity.voltage,
        PowerKW = entity.powerKw,
        CurrentType = currentType?.toDomainModel(),
        CurrentTypeID = entity.currentTypeId,
        LevelID = entity.levelId,
        Level = level.toDomainModel(),
        Quantity = entity.quantity,
        Comments = entity.comments,
    )
}