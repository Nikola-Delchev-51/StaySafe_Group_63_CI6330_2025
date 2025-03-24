package com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO
import androidx.room.Dao
import androidx.room.Query
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.User

@Dao
interface UserDAO : BaseDAO<User> {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}