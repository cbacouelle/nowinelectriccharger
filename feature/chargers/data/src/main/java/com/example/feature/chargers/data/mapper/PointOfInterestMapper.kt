package com.example.feature.chargers.data.mapper

import com.example.feature.chargers.data.api.AddressInfoDto
import com.example.feature.chargers.data.api.MediaItemDto
import com.example.feature.chargers.data.api.PointOfInterestDto
import com.example.feature.chargers.data.api.UserCommentDto
import com.example.feature.chargers.data.api.UserCommentTypeDto
import com.example.feature.chargers.data.api.UserDto
import com.example.feature.chargers.domain.model.AddressInfo
import com.example.feature.chargers.domain.model.CommentType
import com.example.feature.chargers.domain.model.MediaItem
import com.example.feature.chargers.domain.model.PointOfInterest
import com.example.feature.chargers.domain.model.StatusType
import com.example.feature.chargers.domain.model.User
import com.example.feature.chargers.domain.model.UserComment

class PointOfInterestMapper {

    companion object {
        fun map(pointOfInterestDto: PointOfInterestDto): PointOfInterest = PointOfInterest(
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
            MediaItems = pointOfInterestDto.MediaItems.orEmpty().map(MediaItemMapper::map),
            UserComments = pointOfInterestDto.UserComments.orEmpty().map(UserCommentMapper::map),
            AddressInfo = AddressInfoMapper.map(pointOfInterestDto.AddressInfo)
        )
    }

}

class MediaItemMapper {
    companion object {
        fun map(mediaItemDto: MediaItemDto): MediaItem = MediaItem(
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
            User = UserMapper.map(mediaItemDto.User),
        )
    }
}

class UserCommentMapper {
    companion object {
        fun map(userCommentDto: UserCommentDto): UserComment = UserComment(
            ID = userCommentDto.ID,
            ChargePointID = userCommentDto.ChargePointID,
            CommentTypeID = userCommentDto.CommentTypeID,
            CommentType = CommentTypeMapper.map(userCommentDto.CommentType),
            UserName = userCommentDto.UserName,
            Comment = userCommentDto.Comment,
            RelatedURL = userCommentDto.RelatedURL,
            DateCreated = userCommentDto.DateCreated,
            User = userCommentDto.User?.let { UserMapper.map(it) },
            CheckinStatusTypeID = userCommentDto.CheckinStatusTypeID,
        )
    }
}

class CommentTypeMapper {
    companion object {
        fun map(commentTypeDto: UserCommentTypeDto): CommentType = CommentType(
            ID = commentTypeDto.ID,
            Title = commentTypeDto.Title,
        )
    }
}

class UserMapper {
    companion object {
        fun map(userDto: UserDto): User = User(
            ID = userDto.ID,
            Username = userDto.Username,
            ReputationPoints = userDto.ReputationPoints,
            ProfileImageURL = userDto.ProfileImageURL,
        )
    }
}

class AddressInfoMapper {
    companion object {
        fun map(addressInfoDto: AddressInfoDto): AddressInfo =
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
                Distance = formatDistance(addressInfoDto.Distance),
                Title = addressInfoDto.Title,
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
