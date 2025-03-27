package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Position


/*
* @author K2336620
*
* Data access object interface for the Position entity.
* */
@Dao
interface PositionDAO : BaseDAO<Position> {
    @Query("SELECT * FROM positions")
    suspend fun getAllPositions(): List<Position>
}
