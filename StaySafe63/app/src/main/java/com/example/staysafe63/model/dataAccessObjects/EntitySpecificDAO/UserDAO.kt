package com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import com.example.staysafe63.model.entities.User


/**
* @author K2336620
*
* Data access object interface for the User entity.
* */
@Dao
interface UserDAO : BaseDAO<User> {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}