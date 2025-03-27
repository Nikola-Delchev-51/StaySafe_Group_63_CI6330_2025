package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Status

/**
* @author K2336620
*
* Data access object interface for the Status entity.
* */
@Dao
interface StatusDAO : BaseDAO<Status> {
    @Query("SELECT * FROM statuses")
    suspend fun getAllStatuses(): List<Status>
}
