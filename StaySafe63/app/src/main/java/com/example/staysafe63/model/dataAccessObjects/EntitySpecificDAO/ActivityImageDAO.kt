package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.ActivityImage

@Dao
interface ActivityImageDAO : BaseDAO<ActivityImage> {
    @Query("SELECT * FROM activity_images")
    suspend fun getAllActivityImages(): List<ActivityImage>

    @Query("SELECT * FROM activity_images WHERE ActivityID = :activityId")
    suspend fun getImagesForActivity(activityId: Int): List<ActivityImage>
}