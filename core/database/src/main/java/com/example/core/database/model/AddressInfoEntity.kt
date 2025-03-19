package com.example.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "address_info"
)
data class AddressInfoEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "address_line_1")
    val addressLine1: String?,

    @ColumnInfo(name = "address_line_2")
    val addressLine2: String?,

    @ColumnInfo(name = "town")
    val town: String?,

    @ColumnInfo(name = "state_or_province")
    val stateOrProvince: String?,

    @ColumnInfo(name = "postcode")
    val postcode: String?,

    @ColumnInfo(name = "country_id")
    val countryId: Int?,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "contact_telephone_1")
    val contactTelephone1: String?,

    @ColumnInfo(name = "contact_telephone_2")
    val contactTelephone2: String?,

    @ColumnInfo(name = "contact_email")
    val contactEmail: String?,

    @ColumnInfo(name = "access_comments")
    val accessComments: String?,

    @ColumnInfo(name = "related_url")
    val relatedUrl: String?,

    @ColumnInfo(name = "distance")
    val distance: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "charge_point_id", index = true)
    val chargePointId: Int,
)