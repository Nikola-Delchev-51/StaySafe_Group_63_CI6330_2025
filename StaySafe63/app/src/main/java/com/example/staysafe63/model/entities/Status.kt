package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
* @author K2336620
*
* Class that represents a Status object for the staysafe63-database.
*
* */
@Entity(tableName = "statuses")
data class Status(

    @PrimaryKey(autoGenerate = true) val StatusID: Int = 0,

    val StatusName: String,

    val StatusOrder: Int,

)