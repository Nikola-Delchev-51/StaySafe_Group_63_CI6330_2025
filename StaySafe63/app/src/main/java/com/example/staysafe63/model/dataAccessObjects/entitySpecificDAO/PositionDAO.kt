package com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.Position

@Dao
interface PositionDAO : BaseDAO<Position> {
    @Query("SELECT * FROM positions")
    suspend fun getAllPositions(): List<Position>
}
