package com.example.feature.chargers.data.api

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
data class UserCommentDto(
    val ID: Int,
    val ChargePointID: Int,
    val CommentTypeID: Int,
    val CommentType: UserCommentTypeDto,
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
data class UserCommentTypeDto(
    val ID: Int,
    val Title: String,
)