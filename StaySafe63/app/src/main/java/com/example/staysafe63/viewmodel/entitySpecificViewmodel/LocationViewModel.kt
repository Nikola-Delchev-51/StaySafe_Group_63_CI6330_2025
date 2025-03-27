package com.example.staysafe63.viewmodel.entitySpecificViewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
* @author K2336620
*
* ViewModel for managing Location entities.
* This class extends BaseViewModel to inherit common CRUD operations
* and specifies the entity type.
* */
class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseInstance.getDatabase(application)
    private val locationDAO = db.locationDao()
    var locationList = mutableListOf<Location>()


    init {
        viewModelScope.launch {
            locationList.addAll(locationDAO.getAllLocations())
        }
    }


    fun createLocation(name: String,
                   description: String,
                   address: String,
                   postcode: String,
                   latitude: Double,
                   longitude: Double) {
        val newLocation = Location(
            LocationName = name,
            LocationDescription = description,
            LocationAddress = address,
            LocationPostcode = postcode,
            LocationLatitude = latitude,
            LocationLongitude = longitude,
        )

        viewModelScope.launch(Dispatchers.IO) {
            locationDAO.insert(newLocation)
            locationList.add(newLocation)
        }
    }

    fun updateLocation(location: Location,
                   newName: String? = null,
                   newDescription: String? = null,
                   newAddress: String? = null,
                   newPostcode: String? = null,
                   newLatitude: Double? = 0.0,
                   newLongitude: Double? = 0.0) {
        val updatedLocation = location.copy(
            LocationName = newName ?: location.LocationName,
            LocationDescription = newDescription ?: location.LocationDescription,
            LocationAddress = newAddress ?: location.LocationAddress,
            LocationPostcode = newPostcode ?: location.LocationPostcode,
            LocationLatitude = newLatitude ?: location.LocationLatitude,
            LocationLongitude = newLongitude ?: location.LocationLongitude,
        )

        viewModelScope.launch(Dispatchers.IO) {
            locationDAO.update(updatedLocation)
            for (i in locationList.indices) {
                if (locationList[i].LocationID == location.LocationID) {
                    locationList[i] = updatedLocation
                    break
                }
            }
        }
    }

    fun deleteLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            locationDAO.delete(location)
            locationList.remove(location)
        }
    }
}