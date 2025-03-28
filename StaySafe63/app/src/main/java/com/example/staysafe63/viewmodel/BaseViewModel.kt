package com.example.staysafe63.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.staysafe63.model.dataAccessObjects.BaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
* @author K2336620
*
* Base ViewModel class providing a blueprint for the common CRUD operations for entities.
* */
abstract class BaseViewModel<T>(
    private val dao: BaseDAO<T>,
    application: android.app.Application
) : AndroidViewModel(application) {

    val itemList = mutableStateListOf<T>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            itemList.addAll(loadAllItems())
        }
    }

    protected abstract suspend fun loadAllItems(): List<T>

    fun createItem(item: T) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(item)
            itemList.add(item)
        }
    }

    fun updateItem(originalItem: T, updatedItem: T) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(updatedItem)
            val index = itemList.indexOfFirst { it == originalItem }
            if (index != -1) itemList[index] = updatedItem
        }
    }

    fun deleteItem(item: T) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(item)
            itemList.remove(item)
        }
    }
}
