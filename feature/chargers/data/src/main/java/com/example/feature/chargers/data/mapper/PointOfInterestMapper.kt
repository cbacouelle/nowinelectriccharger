package com.example.feature.chargers.data.mapper

import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.CommentTypeEntity
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.database.model.UserEntity
import com.example.feature.chargers.data.api.AddressInfoDto
import com.example.feature.chargers.data.api.MediaItemDto
import com.example.feature.chargers.data.api.PointOfInterestDto
import com.example.feature.chargers.data.api.UserCommentDto
import com.example.feature.chargers.data.api.CommentTypeDto
import com.example.feature.chargers.data.api.UserDto
import com.example.core.model.AddressInfo
import com.example.core.model.CommentType
import com.example.core.model.MediaItem
import com.example.core.model.PointOfInterest
import com.example.core.model.StatusType
import com.example.core.model.User
import com.example.core.model.UserComment

class PointOfInterestMapper {

    companion object {
        fun toDomainModel(pointOfInterestDto: PointOfInterestDto): PointOfInterest =
            PointOfInterest(
                ID = pointOfInterestDto.ID,
                UUID = pointOfInterestDto.UUID,
                IsRecentlyVerified = pointOfInterestDto.IsRecentlyVerified,
                DateLastVerified = pointOfInterestDto.DateLastVerified,
                UsageCost = pointOfInterestDto.UsageCost,
                NumberOfPoints = pointOfInterestDto.NumberOfPoints,
                GeneralComments = pointOfInterestDto.GeneralComments,
                StatusType = when (pointOfInterestDto.StatusTypeID) {
                    0 -> StatusType.UNKNOWN
                    50 -> StatusType.OPERATIONAL
                    150 -> StatusType.PLANNED_FOR_FUTURE
                    else -> StatusType.UNKNOWN
                },
                DateLastStatusUpdate = pointOfInterestDto.DateLastStatusUpdate,
                MediaItems = pointOfInterestDto.MediaItems.orEmpty().map(MediaItemMapper::toDomainModel),
                UserComments = pointOfInterestDto.UserComments.orEmpty()
                    .map(UserCommentMapper::toDomainModel),
                AddressInfo = AddressInfoMapper.toDomainModel(pointOfInterestDto.AddressInfo)
            )

        fun toEntityModel(pointOfInterestDto: PointOfInterestDto) = PointOfInterestEntity(
            id = pointOfInterestDto.ID,
            uuid = pointOfInterestDto.UUID,
            isRecentlyVerified = pointOfInterestDto.IsRecentlyVerified,
            dateLastVerified = pointOfInterestDto.DateLastVerified,
            usageCost = pointOfInterestDto.UsageCost,
            numberOfPoints = pointOfInterestDto.NumberOfPoints,
            generalComments = pointOfInterestDto.GeneralComments,
            statusType = when (pointOfInterestDto.StatusTypeID) {
                0 -> StatusType.UNKNOWN
                50 -> StatusType.OPERATIONAL
                150 -> StatusType.PLANNED_FOR_FUTURE
                else -> StatusType.UNKNOWN
            },
            dateLastStatusUpdate = pointOfInterestDto.DateLastStatusUpdate,
            addressInfoId = pointOfInterestDto.AddressInfo.ID
        )

    }

}

class MediaItemMapper {
    companion object {
        fun toDomainModel(mediaItemDto: MediaItemDto): MediaItem =
            MediaItem(
                ID = mediaItemDto.ID,
                ChargePointID = mediaItemDto.ChargePointID,
                ItemURL = mediaItemDto.ItemURL,
                ItemThumbnailURL = mediaItemDto.ItemThumbnailURL,
                Comment = mediaItemDto.Comment,
                IsEnabled = mediaItemDto.IsEnabled,
                DateCreated = mediaItemDto.DateCreated,
                IsVideo = mediaItemDto.IsVideo,
                IsFeaturedItem = mediaItemDto.IsFeaturedItem,
                IsExternalResource = mediaItemDto.IsExternalResource,
                User = UserMapper.toDomainModel(mediaItemDto.User),
            )

            fun toEntityModel(mediaItemDto: MediaItemDto): MediaItemEntity =
                MediaItemEntity(
                    id = mediaItemDto.ID,
                    chargePointId = mediaItemDto.ChargePointID,
                    itemUrl = mediaItemDto.ItemURL,
                    itemThumbnailUrl = mediaItemDto.ItemThumbnailURL,
                    comment = mediaItemDto.Comment,
                    isEnabled = mediaItemDto.IsEnabled,
                    dateCreated = mediaItemDto.DateCreated,
                    isVideo = mediaItemDto.IsVideo,
                    isFeaturedItem = mediaItemDto.IsFeaturedItem,
                    isExternalResource = mediaItemDto.IsExternalResource,
                    userId = mediaItemDto.User.ID
                )
    }
}

class UserCommentMapper {
    companion object {
        fun toDomainModel(userCommentDto: UserCommentDto): UserComment =
            UserComment(
                ID = userCommentDto.ID,
                ChargePointID = userCommentDto.ChargePointID,
                CommentTypeID = userCommentDto.CommentTypeID,
                CommentType = CommentTypeMapper.toDomainModel(userCommentDto.CommentType),
                UserName = userCommentDto.UserName,
                Comment = userCommentDto.Comment,
                RelatedURL = userCommentDto.RelatedURL,
                DateCreated = userCommentDto.DateCreated,
                User = userCommentDto.User?.let { UserMapper.toDomainModel(it) },
                CheckinStatusTypeID = userCommentDto.CheckinStatusTypeID,
            )

        fun toEntityModel(userCommentDto: UserCommentDto): UserCommentEntity =
            UserCommentEntity(
                id = userCommentDto.ID,
                chargePointId = userCommentDto.ChargePointID,
                commentTypeId = userCommentDto.CommentTypeID,
                username = userCommentDto.UserName,
                comment = userCommentDto.Comment,
                relatedUrl = userCommentDto.RelatedURL,
                dateCreated = userCommentDto.DateCreated,
                userId = userCommentDto.User?.ID,
                checkinStatusTypeID = userCommentDto.CheckinStatusTypeID
            )
    }
}

class CommentTypeMapper {
    companion object {
        fun toDomainModel(commentTypeDto: CommentTypeDto): CommentType =
            CommentType(
                ID = commentTypeDto.ID,
                Title = commentTypeDto.Title,
            )

        fun toEntityModel(commentTypeDto: CommentTypeDto): CommentTypeEntity =
            CommentTypeEntity(
                id = commentTypeDto.ID,
                title = commentTypeDto.Title,
            )
    }
}

class UserMapper {
    companion object {
        fun toDomainModel(userDto: UserDto): User = User(
            ID = userDto.ID,
            Username = userDto.Username,
            ReputationPoints = userDto.ReputationPoints,
            ProfileImageURL = userDto.ProfileImageURL,
        )

        fun toEntityModel(userDto: UserDto) = UserEntity(
            id = userDto.ID,
            username = userDto.Username,
            reputationPoints = userDto.ReputationPoints,
            profileImageURL = userDto.ProfileImageURL
        )
    }
}

class AddressInfoMapper {
    companion object {
        fun toDomainModel(addressInfoDto: AddressInfoDto): AddressInfo =
            AddressInfo(
                ID = addressInfoDto.ID,
                AddressLine1 = addressInfoDto.AddressLine1,
                AddressLine2 = addressInfoDto.AddressLine2,
                Town = addressInfoDto.Town,
                StateOrProvince = addressInfoDto.StateOrProvince,
                Postcode = addressInfoDto.Postcode,
                CountryID = addressInfoDto.CountryID,
                Latitude = addressInfoDto.Latitude,
                Longitude = addressInfoDto.Longitude,
                ContactTelephone1 = addressInfoDto.ContactTelephone1,
                ContactTelephone2 = addressInfoDto.ContactTelephone2,
                ContactEmail = addressInfoDto.AccessComments,
                AccessComments = addressInfoDto.AccessComments,
                RelatedURL = addressInfoDto.RelatedURL,
                ReadableDistance = formatDistance(addressInfoDto.Distance),
                Distance = addressInfoDto.Distance,
                Title = addressInfoDto.Title,
            )

        fun toEntityModel(addressInfoDto: AddressInfoDto, pointOfInterestDto: PointOfInterestDto) = AddressInfoEntity(
            id = addressInfoDto.ID,
            addressLine1 = addressInfoDto.AddressLine1,
            addressLine2 = addressInfoDto.AddressLine2,
            town = addressInfoDto.Town,
            stateOrProvince = addressInfoDto.StateOrProvince,
            postcode = addressInfoDto.Postcode,
            countryId = addressInfoDto.CountryID,
            latitude = addressInfoDto.Latitude,
            longitude = addressInfoDto.Longitude,
            contactTelephone1 = addressInfoDto.ContactTelephone1,
            contactTelephone2 = addressInfoDto.ContactTelephone2,
            contactEmail = addressInfoDto.ContactEmail,
            accessComments = addressInfoDto.AccessComments,
            relatedUrl = addressInfoDto.RelatedURL,
            distance = addressInfoDto.Distance,
            readableDistance = formatDistance(addressInfoDto.Distance),
            title = addressInfoDto.Title,
            chargePointId = pointOfInterestDto.ID
        )

        private fun formatDistance(distanceKm: Double?): String {
            if (distanceKm == null) {
                return ""
            }
            return if (distanceKm < 1) {
                "${(distanceKm * 1000).toInt()} m"
            } else {
                "${"%.2f".format(distanceKm)} km"
            }
        }

    }


}
