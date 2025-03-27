package com.example.staysafe63.viewmodel.entitySpecificViewmodel

import android.app.Application
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.Activity
import com.example.staysafe63.viewmodel.BaseViewModel


/*
* @author K2336620
*
* ViewModel for managing Activity entities.
* This class extends BaseViewModel to inherit common CRUD operations
* and specifies the entity type.
* */
class ActivityViewModel(application: Application) : BaseViewModel<Activity>(
    dao = DatabaseInstance.getDatabase(application).activityDao(),
    application = application
) {
    public override suspend fun loadAllItems(): List<Activity> {
        return DatabaseInstance.getDatabase(getApplication()).activityDao().getAllActivities()
    }

    fun createActivity(
        name: String,
        userId: Int,
        username: String,
        description: String,
        fromId: Int,
        fromName: String,
        leave: Long,
        toId: Int,
        toName: String,
        arrive: String,
        statusId: Int,
        statusName: String
    ) {
        createItem(
            Activity(
                ActivityName = name,
                ActivityUserID = userId,
                ActivityUserUsername = username,
                ActivityDescription = description,
                ActivityFromID = fromId,
                ActivityFromName = fromName,
                ActivityLeave = leave,
                ActivityToID = toId,
                ActivityToName = toName,
                ActivityArrive = arrive,
                ActivityStatusID = statusId,
                ActivityStatusName = statusName
            )
        )
    }

    fun updateActivity(original: Activity, updated: Activity) {
        updateItem(original, updated)
    }
}
