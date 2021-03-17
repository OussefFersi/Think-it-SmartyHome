package com.thinkit.smartyhome.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomViewModel : ViewModel() {
    var lst = MutableLiveData<ArrayList<Room>>()
    var newlist = arrayListOf<Room>()

    fun add(room: Room){
        newlist.add(room)
        lst.value=newlist

    }


}