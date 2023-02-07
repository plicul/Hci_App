package com.example.hci_app.models

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