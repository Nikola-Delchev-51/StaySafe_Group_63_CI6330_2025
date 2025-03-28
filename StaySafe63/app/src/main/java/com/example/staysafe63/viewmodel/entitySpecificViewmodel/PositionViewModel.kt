package com.example.staysafe63.viewmodel.entitySpecificViewmodel

import android.app.Application
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.Position
import com.example.staysafe63.viewmodel.BaseViewModel


/**
* @author K2336620
*
* ViewModel for managing Position entities.
* This class extends BaseViewModel to inherit common CRUD operations
* and specifies the entity type.
* */
class PositionViewModel(application: Application) : BaseViewModel<Position>(
    dao = DatabaseInstance.getDatabase(application).positionDao(),
    application = application
) {
    override suspend fun loadAllItems(): List<Position> {
        return DatabaseInstance.getDatabase(getApplication()).positionDao().getAllPositions()
    }

    fun createPosition(
        activityId: Int,
        activityName: String,
        latitude: Double,
        longitude: Double,
        timestamp: Long
    ) {
        createItem(
            Position(
                PositionActivityID = activityId,
                PositionActivityName = activityName,
                PositionLatitude = latitude,
                PositionLongitude = longitude,
                PositionTimestamp = timestamp
            )
        )
    }

    fun updatePosition(original: Position, updated: Position) {
        updateItem(original, updated)
    }
}