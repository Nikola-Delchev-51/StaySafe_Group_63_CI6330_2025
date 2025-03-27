package com.example.staysafe63.model

import android.content.Context
import android.util.Log
import androidx.room.Room

/**
* @author K2336620
*
* Singleton object that provides a single instance of the AppDatabase.
* */
object DatabaseInstance {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    /*
    * Returns the singleton instance of AppDatabase.
    * If the instance is null, it sets up a new database instance.
    * */
    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "staysafe63-database"
            )
                .build()
        }
        Log.d("DatabaseInstance", "Creating new database instance")
        return INSTANCE!!
    }
}