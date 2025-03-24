package com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Activity

@Dao
interface ActivityDAO : BaseDAO<Activity> {
    @Query("SELECT * FROM activities")
    suspend fun getAllActivities(): List<Activity>
}
