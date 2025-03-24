package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Contact

@Dao
interface ContactDAO : BaseDAO<Contact> {
    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>
}