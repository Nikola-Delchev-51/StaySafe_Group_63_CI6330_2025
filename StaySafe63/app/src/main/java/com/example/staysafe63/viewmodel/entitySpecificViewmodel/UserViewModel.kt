package com.example.staysafe63.viewmodel.entitySpecificViewmodel

import android.app.Application
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.User
import com.example.staysafe63.viewmodel.BaseViewModel

class UserViewModel(application: Application) : BaseViewModel<User>(
    dao = DatabaseInstance.getDatabase(application).userDao(),
    application = application
) {
    public override suspend fun loadAllItems(): List<User> {
        return DatabaseInstance.getDatabase(getApplication()).userDao().getAllUsers()
    }

    fun createUser(
        firstname: String,
        lastname: String,
        phone: String,
        username: String,
        password: String,
        latitude: Double,
        longitude: Double,
        timestamp: Long
    ) {
        createItem(
            User(
                UserFirstname = firstname,
                UserLastname = lastname,
                UserPhone = phone,
                UserUsername = username,
                UserPassword = password,
                UserLatitude = latitude,
                UserLongitude = longitude,
                UserTimestamp = timestamp
            )
        )
    }

    fun updateUser(original: User, updated: User) {
        updateItem(original, updated)
    }
}
