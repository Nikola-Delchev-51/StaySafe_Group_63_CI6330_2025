package com.example.staysafe63.model.dataAccessObjects

import androidx.room.*

/**
* @author K2336620
*
* Base Data access object interface providing common database operations.
* Aims to reduce code repetition by providing a blueprint for all the entity-specific
* Data access objects.
* */
@Dao
interface BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Update
    fun update(item: T)

    @Delete
    fun delete(item: T)
}