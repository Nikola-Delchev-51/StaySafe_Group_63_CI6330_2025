package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val ContactID: Int = 0,
    val ContactUserID: Int = 0,
    val ContactContactID: Int = 0,
    val ContactLabel: String,
    val ContactDateCreated: Long
)