package com.example.feature.chargers.data.api

import com.example.core.model.AddressInfo
import com.example.core.model.UsageType
import kotlinx.serialization.Serializable

@Serializable
data class PointOfInterestDto(
    val ID: Int,
    val UUID: String,
    val IsRecentlyVerified: Boolean,
    val DateLastVerified: String,
    val UsageCost: String?,
    val NumberOfPoints: Int?,
    val GeneralComments: String?,
    val StatusTypeID: Int,
    val DateLastStatusUpdate: String,
    val MediaItems: List<MediaItemDto>?,
    val AddressInfo: AddressInfoDto,
    val UserComments: List<UserCommentDto>?,
    val UsageType: UsageTypeDto,
    val DataProvider: DataProviderDto,
    val StatusType: StatusTypeDto,
    val Connections: List<ConnectionDto>?,
    val OperatorInfo: OperatorInfoDto?,
)

@Serializable
data class ConnectionDto(
    val ID: Int,
    val ConnectionTypeID: Int,
    val ConnectionType: ConnectionTypeDto,
    val Reference: String?,
    val StatusTypeID: Int?,
    val Amps: Int?,
    val Voltage: Int?,
    val PowerKW: Int?,
    val CurrentTypeID: Int?,
    val CurrentType: CurrentTypeDto?,
    val LevelID: Int,
    val Level: LevelDto,
    val Quantity: Int?,
    val Comments: String?,

    )

@Serializable
data class ConnectionTypeDto(
    val ID: Int,
    val IsDiscontinued: Boolean?,
    val IsObsolete: Boolean?,
    val FormalName: String,
    val Title: String,
)

@Serializable
data class CurrentTypeDto(
    val ID: Int,
    val Title: String,
)

@Serializable
data class LevelDto(
    val ID: Int,
    val Title: String,
    val IsFastChargeCapable: Boolean,
    val Comments: String?,
)

@Serializable
data class MediaItemDto(
    val ID: Int,
    val ChargePointID: Int,
    val ItemURL: String,
    val ItemThumbnailURL: String,
    val Comment: String,
    val IsEnabled: Boolean,
    val DateCreated: String,
    val IsVideo: Boolean,
    val IsFeaturedItem: Boolean,
    val IsExternalResource: Boolean,
    val User: UserDto,
)

@Serializable
data class AddressInfoDto(
    val ID: Int,
    val AddressLine1: String?,
    val AddressLine2: String?,
    val Town: String?,
    val StateOrProvince: String?,
    val Postcode: String?,
    val CountryID: Int?,
    val Latitude: Double,
    val Longitude: Double,
    val ContactTelephone1: String?,
    val ContactTelephone2: String?,
    val ContactEmail: String?,
    val AccessComments: String?,
    val RelatedURL: String?,
    val Distance: Double?,
    val DistanceUnit: Int?,
    val Title: String?,
)

@Serializable
data class OperatorInfoDto(
    val ID: Int,
    val Title: String?,
    val WebsiteURL: String?,
    val Comments: String?,
    val PhonePrimaryContact: String?,
    val PhoneSecondaryContact: String?,
    val IsPrivateIndividual: Boolean?,
    val AddressInfo: AddressInfo?,
    val BookingURL: String?,
    val ContactEmail: String?,
    val FaultReportEmail: String?,
    val IsRestrictedEdit: Boolean?,
)

@Serializable
data class StatusTypeDto(
    val ID: Int,
    val Title: String,
    val IsOperational: Boolean?,
    val IsUserSelectable: Boolean?,
)

@Serializable
data class DataProviderDto(
    val ID: Int,
    val WebsiteURL: String?,
    val Comments: String?,
    val IsRestrictedEdit: Boolean,
    val IsOpenDataLicensed: Boolean?,
    val IsApprovedImport: Boolean?,
    val License: String?,
    val DateLastImported: String?,
    val Title: String?
)

@Serializable
data class UsageTypeDto(
    val IsPayAtLocation: Boolean?,
    val IsMembershipRequired: Boolean?,
    val IsAccessKeyRequired: Boolean?,
    val ID: Int,
    val Title: String
)

@Serializable
data class UserCommentDto(
    val ID: Int,
    val ChargePointID: Int,
    val CommentTypeID: Int,
    val CommentType: CommentTypeDto,
    val UserName: String,
    val Comment: String,
    val RelatedURL: String?,
    val DateCreated: String,
    val User: UserDto?,
    val CheckinStatusTypeID: Int,
)

@Serializable
data class UserDto(
    val ID: Int,
    val Username: String,
    val ReputationPoints: Int,
    val ProfileImageURL: String,
)

@Serializable
data class CommentTypeDto(
    val ID: Int,
    val Title: String,
)