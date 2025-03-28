package com.example.staysafe63.model

import androidx.room.*
import com.example.staysafe63.model.dataAccessObjects.EntitySpecificDAO.*
import com.example.staysafe63.model.entities.*


/**
* @author K2336620
*
* Main database class of the application.
* */
@Database(entities = [
    User::class,
    Contact::class,
    Activity::class,
    Location::class,
    Status::class,
    Position::class,
    ActivityImage::class
    ], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    abstract fun contactDao(): ContactDAO

    abstract fun activityDao(): ActivityDAO

    abstract fun locationDao(): LocationDAO

    abstract fun statusDao(): StatusDAO

    abstract fun positionDao(): PositionDAO

    abstract fun activityImageDao(): ActivityImageDAO

}