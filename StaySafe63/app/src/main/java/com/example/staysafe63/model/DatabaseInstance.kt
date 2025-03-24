package com.example.staysafe63.model

import android.content.Context
import android.util.Log
import androidx.room.Room

object DatabaseInstance {
    @Volatile
    private var INSTANCE: AppDatabase? = null

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