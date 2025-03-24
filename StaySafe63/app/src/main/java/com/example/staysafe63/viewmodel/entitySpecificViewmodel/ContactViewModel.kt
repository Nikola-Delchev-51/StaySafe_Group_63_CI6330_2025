package com.example.staysafe63.viewmodel.entitySpecificViewmodel

import android.app.Application
import com.example.staysafe63.model.DatabaseInstance
import com.example.staysafe63.model.entities.Contact
import com.example.staysafe63.viewmodel.BaseViewModel

class ContactViewModel(application: Application) : BaseViewModel<Contact>(
    dao = DatabaseInstance.getDatabase(application).contactDao(),
    application = application
) {
    override suspend fun loadAllItems(): List<Contact> {
        return DatabaseInstance.getDatabase(getApplication()).contactDao().getAllContacts()
    }

    fun createContact(
        userId: Int,
        contactId: Int,
        label: String,
        dateCreated: Long
    ) {
        createItem(
            Contact(
                ContactUserID = userId,
                ContactContactID = contactId,
                ContactLabel = label,
                ContactDateCreated = dateCreated
            )
        )
    }

    fun updateContact(original: Contact, updated: Contact) {
        updateItem(original, updated)
    }
}