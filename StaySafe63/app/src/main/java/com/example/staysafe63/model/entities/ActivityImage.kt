package com.example.staysafe63.model.entities

import androidx.room.*

@Entity(tableName = "activity_images")
data class ActivityImage(
    @PrimaryKey(autoGenerate = true) val ImageID: Int = 0,
    val ActivityID: Int,
    val ImagePath: String,
    val Timestamp: Long = System.currentTimeMillis()
)
