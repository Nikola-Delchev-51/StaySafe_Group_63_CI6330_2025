package com.example.staysafe63.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/*
* @author K2336620
*
* Class that represents a Contact object for the staysafe63-database.
*
* */
@Entity(tableName = "contacts")
data class Contact(

    @PrimaryKey(autoGenerate = true) val ContactID: Int = 0,

    val ContactUserID: Int = 0,

    val ContactContactID: Int = 0,

    val ContactLabel: String,

    val ContactDateCreated: Long

)