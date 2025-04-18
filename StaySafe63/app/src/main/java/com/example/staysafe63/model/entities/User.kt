package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
* @author K2336620
*
* Class that represents a User object for the staysafe63-database.
*
* */
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true) val UserID: Int = 0,

    val UserFirstname: String,

    val UserLastname: String,

    val UserPhone: String,

    val UserUsername: String,

    val UserPassword: String,

    val UserLatitude: Double,

    val UserLongitude: Double,

    val UserTimestamp: Long

)