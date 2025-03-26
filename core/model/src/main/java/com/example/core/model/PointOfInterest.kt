package com.example.core.model

import kotlinx.serialization.Serializable

@Serializable
data class PointOfInterest(
    val ID: Int,
    val UUID: String,
    val IsRecentlyVerified: Boolean,
    val DateLastVerified: String,
    val UsageCost: String?,
    val NumberOfPoints: Int?,
    val GeneralComments: String?,
    val StatusType: StatusType,
    val DateLastStatusUpdate: String,
    val UserComments: List<UserComment>,
    val MediaItems: List<MediaItem>,
    val AddressInfo: AddressInfo,
    val OperatorInfo: OperatorInfo?,
    val UsageTye: UsageType,
    val DataProvider: DataProvider?,
    val Connections: List<Connection>?
)

@Serializable
data class Connection(
    val Id: Int,
    val ConnectionTypeID: Int,
    val ConnectionType: ConnectionType,
    val Reference: String?,
    val StatusTypeID: Int?,
    val Amps: Int?,
    val Voltage: Int?,
    val PowerKW: Int?,
    val CurrentTypeID: Int?,
    val CurrentType: CurrentType?,
    val LevelID: Int?,
    val Level: Level?,
    val Quantity: Int?,
    val Comments: String?,

    )

@Serializable
data class ConnectionType(
    val ID: Int,
    val IsDiscontinued: Boolean?,
    val IsObsolete: Boolean?,
    val FormalName: String,
    val Title: String,
)

@Serializable
data class CurrentType(
    val ID: Int,
    val Title: String,
)

@Serializable
data class Level(
    val ID: Int,
    val Title: String,
    val IsFastChargeCapable: Boolean,
    val Comments: String?,
)

@Serializable
data class DataProvider(
    val WebsiteURL: String?,
    val Comments: String?,
    val IsRestrictedEdit: Boolean?,
    val IsOpenDataLicensed: Boolean?,
    val IsApprovedImport: Boolean?,
    val License: String?,
    val DateLastImported: String?,
    val Id: Int,
    val Title: String?
)

@Serializable
data class UsageType(
    val IsPayAtLocation: Boolean?,
    val IsMembershipRequired: Boolean?,
    val IsAccessKeyRequired: Boolean?,
    val ID: Int,
    val Title: String
)

@Serializable
data class StatusType(
    val ID: Int,
    val Title: String,
    val IsOperational: Boolean?,
    val IsUserSelectable: Boolean?,
)

@Serializable
data class MediaItem(
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
    val User: User,
)

@Serializable
data class User(
    val ID: Int,
    val Username: String,
    val ReputationPoints: Int,
    val ProfileImageURL: String,
)

@Serializable
data class UserComment(
    val ID: Int,
    val ChargePointID: Int,
    val CommentTypeID: Int,
    val CommentType: CommentType,
    val UserName: String,
    val Comment: String,
    val RelatedURL: String?,
    val DateCreated: String,
    val User: User?,
    val CheckinStatusTypeID: Int,
)

@Serializable
data class CommentType(
    val ID: Int,
    val Title: String,
)

@Serializable
data class AddressInfo(
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
    val ReadableDistance: String?,
    val Title: String?,
)

@Serializable
data class OperatorInfo(
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