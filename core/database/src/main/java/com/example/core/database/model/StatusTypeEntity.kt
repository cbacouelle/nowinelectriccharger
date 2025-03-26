package com.example.core.database.model

import android.net.http.UrlRequest.Status
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.DataProvider
import com.example.core.model.StatusType
import com.example.core.model.UsageType

@Entity(
    tableName = "status_type",
)
data class StatusTypeEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "is_user_selectable")
    val isUserSelectable: Boolean?,

    @ColumnInfo(name = "is_operational")
    val isOperational: Boolean?,

) {
    fun toDomainModel() = StatusType(
        ID = id,
        Title = title,
        IsOperational = isOperational,
        IsUserSelectable = isUserSelectable
    )
}