package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.User

@Entity(
    tableName = "user",
)
data class UserEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "reputation_points")
    val reputationPoints: Int,

    @ColumnInfo(name = "profile_image_url")
    val profileImageURL: String
) {
    fun toDomainModel(): User = User(
        ID = id,
        Username = username,
        ReputationPoints = reputationPoints,
        ProfileImageURL = profileImageURL,
    )
}