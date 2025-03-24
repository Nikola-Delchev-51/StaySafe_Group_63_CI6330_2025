package com.example.staysafe63.model.dataAccessObjects

import androidx.room.*

@Dao
interface BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Update
    fun update(item: T)

    @Delete
    fun delete(item: T)
}