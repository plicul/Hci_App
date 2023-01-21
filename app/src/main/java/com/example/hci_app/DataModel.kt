package com.example.hci_app

import android.provider.ContactsContract.Data
import java.io.File

class DataModel(namestr: String) {
    fun addChild(dataModel: DataModel) {
        children.add(dataModel)
    }

    var name = namestr
    var children : ArrayList<DataModel>
    init {
        children = ArrayList()
    }
}