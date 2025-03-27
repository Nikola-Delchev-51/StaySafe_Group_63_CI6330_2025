package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Contact

/**
* @author K2336620
*
* Data access object interface for the Contact entity.
* */
@Dao
interface ContactDAO : BaseDAO<Contact> {
    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>
}