package com.example.feature.chargers.domain.model

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
    val AddressInfo: AddressInfo
)

enum class StatusType {
    OPERATIONAL,
    UNKNOWN,
    PLANNED_FOR_FUTURE
}


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

data class User(
    val ID: Int,
    val Username: String,
    val ReputationPoints: Int,
    val ProfileImageURL: String,
)

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

data class CommentType(
    val ID: Int,
    val Title: String,
)

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
    val Distance: String?,
    val Title: String?,
)