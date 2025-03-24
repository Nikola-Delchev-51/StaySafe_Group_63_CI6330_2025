package com.example.staysafe63.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO.ActivityDAO
import com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO.ContactDAO
import com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO.LocationDAO
import com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO.PositionDAO
import com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO.StatusDAO
import com.example.staysafe63.model.dataAccessObjects.entitySpecificDAO.UserDAO
import com.example.staysafe63.model.entities.*

@Database(entities = [
    User::class,
    Contact::class,
    Activity::class,
    Location::class,
    Status::class,
    Position::class
    ]
    , version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    abstract fun contactDao(): ContactDAO

    abstract fun activityDao(): ActivityDAO

    abstract fun locationDao(): LocationDAO

    abstract fun statusDao(): StatusDAO

    abstract fun positionDao(): PositionDAO

}