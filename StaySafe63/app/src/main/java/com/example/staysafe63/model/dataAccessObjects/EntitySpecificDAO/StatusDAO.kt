package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Status

@Dao
interface StatusDAO : BaseDAO<Status> {
    @Query("SELECT * FROM statuses")
    suspend fun getAllStatuses(): List<Status>
}
