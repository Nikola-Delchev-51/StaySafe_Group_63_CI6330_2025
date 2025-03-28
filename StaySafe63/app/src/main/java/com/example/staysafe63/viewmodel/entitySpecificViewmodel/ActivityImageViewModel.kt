package com.example.staysafe63.viewmodel.entitySpecificViewmodel

import android.app.Application
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.ActivityImage
import com.example.staysafe63.viewmodel.BaseViewModel


/**
 * @author K2336620
 *
 * ViewModel for managing ActivityImage entities.
 * This class extends BaseViewModel to inherit common CRUD operations
 * and specifies the entity type.
 * */
class ActivityImageViewModel(application: Application) : BaseViewModel<ActivityImage>(
    dao = DatabaseInstance.getDatabase(application).activityImageDao(),
    application = application
) {
    public override suspend fun loadAllItems(): List<ActivityImage> {
        return DatabaseInstance.getDatabase(getApplication()).activityImageDao().getAllActivityImages()
    }

    suspend fun getImagesForActivity(activityId: Int): List<ActivityImage> {
        return DatabaseInstance.getDatabase(getApplication()).activityImageDao().getImagesForActivity(activityId)
    }

    fun createActivityImage(
        activityID: Int,
        imagePath: String
    ) {
        createItem(
            ActivityImage(
                ActivityID = activityID,
                ImagePath = imagePath
            )
        )
    }

}
