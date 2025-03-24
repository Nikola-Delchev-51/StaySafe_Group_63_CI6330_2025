package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positions")
data class Position(
    @PrimaryKey(autoGenerate = true) val PositionID: Int = 0,
    val PositionActivityID: Int = 0,
    val PositionActivityName: String,
    val PositionLatitude: Double,
    val PositionLongitude: Double,
    val PositionTimestamp: Long
)