package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.core.model.AddressInfo
import com.example.core.model.OperatorInfo

@Entity(
    tableName = "operator_info"
)
data class OperatorInfoEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "website_url")
    val websiteUrl: String?,

    @ColumnInfo(name = "comments")
    val comments: String?,

    @ColumnInfo(name = "phone_primary_contact")
    val phonePrimaryContact: String?,

    @ColumnInfo(name = "phone_secondary_contact")
    val phoneSecondaryContact: String?,

    @ColumnInfo(name = "is_private_individual")
    val isPrivateIndividual: Boolean?,

    @ColumnInfo(name = "booking_url")
    val bookingUrl: String?,

    @ColumnInfo(name = "contact_email")
    val contactEmail: String?,

    @ColumnInfo(name = "fault_report_email")
    val faultReportEmail: String?,

    @ColumnInfo(name = "is_restricted_edit")
    val isRestrictedEdit: Boolean?,

    @ColumnInfo(name = "address_info_id")
    val addressInfoId: Int?,
)

data class PopulatedOperatorInfo(
    @Embedded val entity: OperatorInfoEntity,

    @Relation(
        parentColumn = "address_info_id",
        entityColumn = "id",
        entity = AddressInfoEntity::class
    )
    val addressInfo: AddressInfoEntity
) {
    fun toDomainModel() = OperatorInfo(
        ID = entity.id,
        Title = entity.title,
        WebsiteURL = entity.websiteUrl,
        Comments = entity.comments,
        PhonePrimaryContact = entity.phonePrimaryContact,
        PhoneSecondaryContact = entity.phoneSecondaryContact,
        IsPrivateIndividual = entity.isPrivateIndividual,
        AddressInfo = addressInfo.toDomainModel(),
        BookingURL = entity.bookingUrl,
        ContactEmail = entity.contactEmail,
        FaultReportEmail = entity.faultReportEmail,
        IsRestrictedEdit = entity.isRestrictedEdit
    )
}

