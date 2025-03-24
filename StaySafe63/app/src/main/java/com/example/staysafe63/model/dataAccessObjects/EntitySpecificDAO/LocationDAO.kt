package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Location

@Dao
interface LocationDAO : BaseDAO<Location> {
    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): List<Location>
}
