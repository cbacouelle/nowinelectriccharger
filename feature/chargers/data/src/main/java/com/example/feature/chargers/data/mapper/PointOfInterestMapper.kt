package com.example.feature.chargers.data.mapper

import com.example.core.database.model.AddressInfoEntity
import com.example.core.database.model.CommentTypeEntity
import com.example.core.database.model.ConnectionEntity
import com.example.core.database.model.ConnectionTypeEntity
import com.example.core.database.model.CurrentTypeEntity
import com.example.core.database.model.DataProviderEntity
import com.example.core.database.model.LevelEntity
import com.example.core.database.model.MediaItemEntity
import com.example.core.database.model.OperatorInfoEntity
import com.example.core.database.model.PointOfInterestEntity
import com.example.core.database.model.StatusTypeEntity
import com.example.core.database.model.UsageTypeEntity
import com.example.core.database.model.UserCommentEntity
import com.example.core.database.model.UserEntity
import com.example.core.model.AddressInfo
import com.example.core.model.CommentType
import com.example.core.model.Connection
import com.example.core.model.ConnectionType
import com.example.core.model.CurrentType
import com.example.core.model.DataProvider
import com.example.core.model.Level
import com.example.core.model.MediaItem
import com.example.core.model.OperatorInfo
import com.example.core.model.PointOfInterest
import com.example.core.model.StatusType
import com.example.core.model.UsageType
import com.example.core.model.User
import com.example.core.model.UserComment
import com.example.feature.chargers.data.api.AddressInfoDto
import com.example.feature.chargers.data.api.CommentTypeDto
import com.example.feature.chargers.data.api.ConnectionDto
import com.example.feature.chargers.data.api.ConnectionTypeDto
import com.example.feature.chargers.data.api.CurrentTypeDto
import com.example.feature.chargers.data.api.DataProviderDto
import com.example.feature.chargers.data.api.LevelDto
import com.example.feature.chargers.data.api.MediaItemDto
import com.example.feature.chargers.data.api.OperatorInfoDto
import com.example.feature.chargers.data.api.PointOfInterestDto
import com.example.feature.chargers.data.api.StatusTypeDto
import com.example.feature.chargers.data.api.UsageTypeDto
import com.example.feature.chargers.data.api.UserCommentDto
import com.example.feature.chargers.data.api.UserDto

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
                StatusType = StatusTypeMapper.toDomainModel(pointOfInterestDto.StatusType),
                DateLastStatusUpdate = pointOfInterestDto.DateLastStatusUpdate,
                MediaItems = pointOfInterestDto.MediaItems.orEmpty()
                    .map(MediaItemMapper::toDomainModel),
                UserComments = pointOfInterestDto.UserComments.orEmpty()
                    .map(UserCommentMapper::toDomainModel),
                AddressInfo = AddressInfoMapper.toDomainModel(pointOfInterestDto.AddressInfo),
                UsageTye = UsageTypeMapper.toDomainModel(pointOfInterestDto.UsageType),
                DataProvider = DataProviderMapper.toDomainModel(pointOfInterestDto.DataProvider),
                Connections = pointOfInterestDto.Connections.orEmpty()
                    .map(ConnectionMapper::toDomainModel),
                OperatorInfo = pointOfInterestDto.OperatorInfo?.let {
                    OperatorInfoMapper.toDomainModel(
                        it
                    )
                }
            )

        fun toEntityModel(pointOfInterestDto: PointOfInterestDto) = PointOfInterestEntity(
            id = pointOfInterestDto.ID,
            uuid = pointOfInterestDto.UUID,
            isRecentlyVerified = pointOfInterestDto.IsRecentlyVerified,
            dateLastVerified = pointOfInterestDto.DateLastVerified,
            usageCost = pointOfInterestDto.UsageCost,
            numberOfPoints = pointOfInterestDto.NumberOfPoints,
            generalComments = pointOfInterestDto.GeneralComments,
            statusTypeId = pointOfInterestDto.StatusType.ID,
            dateLastStatusUpdate = pointOfInterestDto.DateLastStatusUpdate,
            addressInfoId = pointOfInterestDto.AddressInfo.ID,
            usageTypeId = pointOfInterestDto.UsageType.ID,
            dataProviderId = pointOfInterestDto.DataProvider.ID,
            operatorInfoId = pointOfInterestDto.OperatorInfo?.ID
        )

    }

}

class ConnectionMapper {
    companion object {
        fun toDomainModel(connectionDto: ConnectionDto): Connection = Connection(
            Id = connectionDto.ID,
            ConnectionTypeID = connectionDto.ConnectionTypeID,
            ConnectionType = ConnectionTypeMapper.toDomainModel(connectionDto.ConnectionType),
            Reference = connectionDto.Reference,
            StatusTypeID = connectionDto.StatusTypeID,
            Amps = connectionDto.Amps,
            Voltage = connectionDto.Voltage,
            PowerKW = connectionDto.PowerKW,
            CurrentTypeID = connectionDto.CurrentTypeID,
            CurrentType = connectionDto.CurrentType?.let {
                CurrentTypeMapper.toDomainModel(
                    connectionDto.CurrentType
                )
            },
            LevelID = connectionDto.LevelID,
            Level = LevelMapper.toDomainModel(connectionDto.Level),
            Quantity = connectionDto.Quantity,
            Comments = connectionDto.Comments
        )


        fun toEntityModel(connectionDto: ConnectionDto): ConnectionEntity = ConnectionEntity(
            id = connectionDto.ID,
            connectionTypeId = connectionDto.ConnectionTypeID,
            statusTypeId = connectionDto.StatusTypeID,
            levelId = connectionDto.LevelID,
            currentTypeId = connectionDto.CurrentTypeID,
            reference = connectionDto.Reference,
            amps = connectionDto.Amps,
            voltage = connectionDto.Voltage,
            powerKw = connectionDto.PowerKW,
            quantity = connectionDto.Quantity,
            comments = connectionDto.Comments
        )
    }
}

class CurrentTypeMapper {
    companion object {
        fun toDomainModel(currentTypeDto: CurrentTypeDto) = CurrentType(
            ID = currentTypeDto.ID,
            Title = currentTypeDto.Title
        )


        fun toEntityModel(currentTypeDto: CurrentTypeDto) = CurrentTypeEntity(
            id = currentTypeDto.ID,
            title = currentTypeDto.Title
        )
    }
}

class LevelMapper {
    companion object {
        fun toDomainModel(currentTypeDto: LevelDto) = Level(
            ID = currentTypeDto.ID,
            Title = currentTypeDto.Title,
            IsFastChargeCapable = currentTypeDto.IsFastChargeCapable,
            Comments = currentTypeDto.Comments
        )


        fun toEntityModel(currentTypeDto: LevelDto) = LevelEntity(
            id = currentTypeDto.ID,
            title = currentTypeDto.Title,
            isFastChargeable = currentTypeDto.IsFastChargeCapable,
            comments = currentTypeDto.Comments
        )
    }
}

class ConnectionTypeMapper {
    companion object {
        fun toDomainModel(connectionTypeDto: ConnectionTypeDto) = ConnectionType(
            ID = connectionTypeDto.ID,
            IsDiscontinued = connectionTypeDto.IsDiscontinued,
            IsObsolete = connectionTypeDto.IsObsolete,
            FormalName = connectionTypeDto.FormalName,
            Title = connectionTypeDto.Title
        )

        fun toEntityModel(connectionTypeDto: ConnectionTypeDto) = ConnectionTypeEntity(
            id = connectionTypeDto.ID,
            isDiscontinued = connectionTypeDto.IsDiscontinued,
            isObsolete = connectionTypeDto.IsObsolete,
            formalName = connectionTypeDto.FormalName,
            title = connectionTypeDto.Title
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

        fun toEntityModel(addressInfoDto: AddressInfoDto, pointOfInterestDto: PointOfInterestDto) =
            AddressInfoEntity(
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

class StatusTypeMapper {
    companion object {
        fun toDomainModel(statusTypeDto: StatusTypeDto) = StatusType(
            ID = statusTypeDto.ID,
            Title = statusTypeDto.Title,
            IsOperational = statusTypeDto.IsOperational,
            IsUserSelectable = statusTypeDto.IsUserSelectable
        )

        fun toEntityModel(statusTypeDto: StatusTypeDto) = StatusTypeEntity(
            id = statusTypeDto.ID,
            title = statusTypeDto.Title,
            isUserSelectable = statusTypeDto.IsUserSelectable,
            isOperational = statusTypeDto.IsOperational
        )
    }
}

class OperatorInfoMapper {
    companion object {
        fun toDomainModel(operatorInfoDto: OperatorInfoDto) = OperatorInfo(
            ID = operatorInfoDto.ID,
            Title = operatorInfoDto.Title,
            WebsiteURL = operatorInfoDto.WebsiteURL,
            Comments = operatorInfoDto.Comments,
            PhonePrimaryContact = operatorInfoDto.PhonePrimaryContact,
            PhoneSecondaryContact = operatorInfoDto.PhoneSecondaryContact,
            IsPrivateIndividual = operatorInfoDto.IsPrivateIndividual,
            AddressInfo = operatorInfoDto.AddressInfo,
            BookingURL = operatorInfoDto.BookingURL,
            ContactEmail = operatorInfoDto.ContactEmail,
            FaultReportEmail = operatorInfoDto.FaultReportEmail,
            IsRestrictedEdit = operatorInfoDto.IsRestrictedEdit
        )

        fun toEntityModel(operatorInfoDto: OperatorInfoDto) = OperatorInfoEntity(
            id = operatorInfoDto.ID,
            title = operatorInfoDto.Title,
            websiteUrl = operatorInfoDto.WebsiteURL,
            comments = operatorInfoDto.Comments,
            phonePrimaryContact = operatorInfoDto.PhonePrimaryContact,
            phoneSecondaryContact = operatorInfoDto.PhoneSecondaryContact,
            isPrivateIndividual = operatorInfoDto.IsPrivateIndividual,
            bookingUrl = operatorInfoDto.BookingURL,
            contactEmail = operatorInfoDto.ContactEmail,
            faultReportEmail = operatorInfoDto.FaultReportEmail,
            isRestrictedEdit = operatorInfoDto.IsRestrictedEdit,
            addressInfoId = operatorInfoDto.AddressInfo?.ID
        )

    }
}

class DataProviderMapper {
    companion object {
        fun toDomainModel(dataProviderDto: DataProviderDto) = DataProvider(
            WebsiteURL = dataProviderDto.WebsiteURL,
            Comments = dataProviderDto.Comments,
            IsRestrictedEdit = dataProviderDto.IsRestrictedEdit,
            IsOpenDataLicensed = dataProviderDto.IsOpenDataLicensed,
            IsApprovedImport = dataProviderDto.IsApprovedImport,
            License = dataProviderDto.License,
            DateLastImported = dataProviderDto.DateLastImported,
            Id = dataProviderDto.ID,
            Title = dataProviderDto.Title
        )

        fun toEntityModel(dataProviderDto: DataProviderDto) = DataProviderEntity(
            id = dataProviderDto.ID,
            websiteUrl = dataProviderDto.WebsiteURL,
            comments = dataProviderDto.Comments,
            isRestrictedEdit = dataProviderDto.IsRestrictedEdit,
            isOpenDataLicensed = dataProviderDto.IsOpenDataLicensed,
            isApprovedImport = dataProviderDto.IsApprovedImport,
            license = dataProviderDto.License,
            dateLastImported = dataProviderDto.DateLastImported,
            title = dataProviderDto.Title
        )
    }
}

class UsageTypeMapper {
    companion object {
        fun toDomainModel(usageTypeDto: UsageTypeDto) = UsageType(
            IsPayAtLocation = usageTypeDto.IsPayAtLocation,
            IsMembershipRequired = usageTypeDto.IsMembershipRequired,
            IsAccessKeyRequired = usageTypeDto.IsAccessKeyRequired,
            ID = usageTypeDto.ID,
            Title = usageTypeDto.Title
        )

        fun toEntityModel(usageTypeDto: UsageTypeDto) = UsageTypeEntity(
            id = usageTypeDto.ID,
            isPayAtLocation = usageTypeDto.IsPayAtLocation,
            isMembershipRequired = usageTypeDto.IsMembershipRequired,
            isAccessKeyRequired = usageTypeDto.IsAccessKeyRequired,
            title = usageTypeDto.Title,
        )

    }
}
