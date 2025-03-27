package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Activity

/*
* @author K2336620
*
* Data access object interface for the Activity entity.
* */
@Dao
interface ActivityDAO : BaseDAO<Activity> {
    @Query("SELECT * FROM activities")
    suspend fun getAllActivities(): List<Activity>
}
