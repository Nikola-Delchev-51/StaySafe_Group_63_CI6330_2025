package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statuses")
data class Status(
    @PrimaryKey(autoGenerate = true) val StatusID: Int = 0,
    val StatusName: String,
    val StatusOrder: Int,
)