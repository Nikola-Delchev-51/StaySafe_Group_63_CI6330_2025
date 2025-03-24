package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey(autoGenerate = true) val ActivityID: Int = 0,
    val ActivityName: String,
    val ActivityUserID: Int = 0,
    val ActivityUserUsername: String,
    val ActivityDescription: String,
    val ActivityFromID: Int = 0,
    val ActivityFromName: String,
    val ActivityLeave: Long,
    val ActivityToID: Int = 0,
    val ActivityToName: String,
    val ActivityArrive: String,
    val ActivityStatusID: Int = 0,
    val ActivityStatusName: String,
)