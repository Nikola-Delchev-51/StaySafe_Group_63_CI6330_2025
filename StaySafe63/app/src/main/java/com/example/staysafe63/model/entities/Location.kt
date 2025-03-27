package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/*
* @author K2336620
*
* Class that represents a Location object for the staysafe63-database.
*
* */
@Entity(tableName = "locations")
data class Location(

    @PrimaryKey(autoGenerate = true) val LocationID: Int = 0,

    val LocationName: String,

    val LocationDescription: String,

    val LocationAddress: String,

    val LocationPostcode: String,

    val LocationLatitude: Double,

    val LocationLongitude : Double,

)