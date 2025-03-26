package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.UsageType

@Entity(
    tableName = "usage_type",
)
data class UsageTypeEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "is_pay_at_location")
    val isPayAtLocation: Boolean?,

    @ColumnInfo(name = "is_membership_required")
    val isMembershipRequired: Boolean?,

    @ColumnInfo(name = "is_access_key_required")
    val isAccessKeyRequired: Boolean?,

    @ColumnInfo(name = "title")
    val title: String,
) {
    fun toDomainModel(): UsageType = UsageType(
        IsPayAtLocation = isPayAtLocation,
        IsMembershipRequired = isMembershipRequired,
        IsAccessKeyRequired = isAccessKeyRequired,
        ID = id,
        Title = title
    )
}