package com.example.binlist.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cardTable"
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "scheme")
    val scheme: String,
    @ColumnInfo(name = "countryName")
    val countryName: String,
    @ColumnInfo(name = "latitude")
    val latitude: Int,
    @ColumnInfo(name = "longitude")
    val longitude: Int,
    @ColumnInfo(name = "bankName")
    val bankName: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "city")
    val city: String,
)