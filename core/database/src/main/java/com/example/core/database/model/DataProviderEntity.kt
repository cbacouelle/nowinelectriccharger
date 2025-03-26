package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.DataProvider
import com.example.core.model.UsageType

@Entity(
    tableName = "data_provider",
)
data class DataProviderEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "website_url")
    val websiteUrl: String?,

    @ColumnInfo(name = "comments")
    val comments: String?,

    @ColumnInfo(name = "is_restricted_edit")
    val isRestrictedEdit: Boolean?,

    @ColumnInfo(name = "is_open_data_licensed")
    val isOpenDataLicensed: Boolean?,

    @ColumnInfo(name = "is_approved_import")
    val isApprovedImport: Boolean?,

    @ColumnInfo(name = "license")
    val license: String?,

    @ColumnInfo(name = "date_last_imported")
    val dateLastImported: String?,

    @ColumnInfo(name = "title")
    val title: String?,
) {
    fun toDomainModel() = DataProvider(
        WebsiteURL = websiteUrl,
        Comments = comments,
        IsRestrictedEdit = isRestrictedEdit,
        IsOpenDataLicensed = isOpenDataLicensed,
        IsApprovedImport = isApprovedImport,
        License = license,
        DateLastImported = dateLastImported,
        Id = id,
        Title = title
    )
}