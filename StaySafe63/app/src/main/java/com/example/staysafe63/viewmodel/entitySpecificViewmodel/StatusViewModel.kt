package com.example.staysafe63.viewmodel.entitySpecificViewmodel

import android.app.Application
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.Status
import com.example.staysafe63.viewmodel.BaseViewModel

class StatusViewModel(application: Application) : BaseViewModel<Status>(
    dao = DatabaseInstance.getDatabase(application).statusDao(),
    application = application
) {
    public override suspend fun loadAllItems(): List<Status> {
        return DatabaseInstance.getDatabase(getApplication()).statusDao().getAllStatuses()
    }

    fun createStatus(
        name: String,
        order: Int
    ) {
        createItem(
            Status(
                StatusName = name,
                StatusOrder = order
            )
        )
    }


}
