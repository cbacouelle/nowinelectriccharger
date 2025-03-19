package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_comment",
    foreignKeys = [
        ForeignKey(
            entity = CommentTypeEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("comment_type_id"),
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
        ),
        ForeignKey(
            entity = PointOfInterestEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("charge_point_id"),
        )
    ],
)
data class UserCommentEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "charge_point_id", index = true)
    val chargePointId: Int,

    @ColumnInfo(name = "comment_type_id", index = true)
    val commentTypeId: Int,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "comment")
    val comment: String,

    @ColumnInfo(name = "related_url")
    val relatedUrl: String,

    @ColumnInfo(name = "date_created")
    val dateCreated: String,

    @ColumnInfo(name = "user_id", index = true)
    val userId: Int,

    @ColumnInfo(name = "checkin_status_type_id")
    val checkinStatusTypeID: Int,
)

//
//data class UserCommentWithUser(
//    @Embedded( prefix = "user_comment_") val entity: UserCommentEntity,
//    @Embedded( prefix = "user_") val user: UserEntity,
////    @Embedded( prefix = "comment_type") val commentType: CommentTypeEntity
//)