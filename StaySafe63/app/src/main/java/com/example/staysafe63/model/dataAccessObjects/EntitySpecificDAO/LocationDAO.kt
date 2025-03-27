package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Location


/**
* @author K2336620
*
* Data access object interface for the Location entity.
* */
@Dao
interface LocationDAO : BaseDAO<Location> {
    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): List<Location>
}
