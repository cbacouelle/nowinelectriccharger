package com.example.core.database.model

import android.net.http.UrlRequest.Status
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.core.model.Connection
import com.example.core.model.ConnectionType
import com.example.core.model.PointOfInterest
import com.example.core.model.StatusType
import java.sql.Ref

@Entity(
    tableName = "connection_type",
)
data class ConnectionTypeEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "is_discontinued")
    val isDiscontinued: Boolean?,

    @ColumnInfo(name = "is_obsolete")
    val isObsolete: Boolean?,

    @ColumnInfo(name = "formal_name")
    val formalName: String,

    @ColumnInfo(name = "title")
    val title: String,
)  {
    fun toDomainModel() = ConnectionType(
        ID = id,
        IsDiscontinued = isDiscontinued,
        IsObsolete = isObsolete,
        FormalName = formalName,
        Title = title
    )
}